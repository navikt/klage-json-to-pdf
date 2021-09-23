package no.nav.klage.pdfgen.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.openhtmltopdf.extend.FSSupplier
import com.openhtmltopdf.outputdevice.helper.BaseRendererBuilder
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder
import com.openhtmltopdf.svgsupport.BatikSVGDrawer
import no.nav.klage.pdfgen.Application
import org.apache.pdfbox.io.IOUtils
import org.jsoup.Jsoup
import org.jsoup.helper.W3CDom
import org.springframework.stereotype.Service
import org.w3c.dom.Document
import java.io.*
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths


val fontsRoot: Path = Paths.get("fonts/")

val objectMapper: ObjectMapper = ObjectMapper()
    .registerKotlinModule()

val colorProfile: ByteArray = IOUtils.toByteArray(Application::class.java.getResourceAsStream("/sRGB2014.icc"))

val fonts: Array<FontMetadata> = objectMapper.readValue(Files.newInputStream(fontsRoot.resolve("config.json")))

data class FontMetadata(
    val family: String,
    val path: String,
    val weight: Int,
    val style: BaseRendererBuilder.FontStyle,
    val subset: Boolean
) {
    val bytes: ByteArray = Files.readAllBytes(fontsRoot.resolve(path))
}


@Service
class PDFGenService {

    fun getPDFAsOutputStream(json: String): ByteArray {

        //TODO json to html..

        val html = """
            <!DOCTYPE html>
            <html>
            <head>
                <title>Test template</title>
                <style>
                    h1 {
                        font-family: "Source Sans Pro" !important;
                    }
                </style>
            </head>
            <body>
            <h1>This document should render fine</h1>
            </body>
            </html>
        """.trimIndent()

        val doc = fromHtmlToDocument(html)

        val os = ByteArrayOutputStream()

        createPDFA(doc, os)

        return os.toByteArray()
    }


    private fun fromHtmlToDocument(html: String): Document {
        val doc = Jsoup.parse(html)
        return W3CDom().fromJsoup(doc)
    }

    private fun createPDFA(w3doc: Document, outputStream: OutputStream) = PdfRendererBuilder()
        .apply {
            for (font in fonts) {
                useFont(FSSupplier(getIs(font.bytes)), font.family, font.weight, font.style, font.subset)
            }
        }
        // .useFastMode() wait with fast mode until it doesn't print a bunch of errors
        .useColorProfile(colorProfile)
        .useSVGDrawer(BatikSVGDrawer())
        .usePdfAConformance(PdfRendererBuilder.PdfAConformance.PDFA_2_U)
        .withW3cDocument(w3doc, "")
        .toStream(outputStream)
        .buildPdfRenderer()
        .createPDF()

    private fun getIs(byteArray: ByteArray): () -> InputStream {
        return { ByteArrayInputStream(byteArray) }
    }

}