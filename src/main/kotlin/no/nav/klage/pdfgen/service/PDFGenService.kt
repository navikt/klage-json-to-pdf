package no.nav.klage.pdfgen.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.openhtmltopdf.extend.FSSupplier
import com.openhtmltopdf.outputdevice.helper.BaseRendererBuilder
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder
import com.openhtmltopdf.svgsupport.BatikSVGDrawer
import no.nav.klage.pdfgen.Application
import no.nav.klage.pdfgen.transformers.HtmlCreator
import org.apache.pdfbox.io.IOUtils
import org.springframework.stereotype.Service
import org.w3c.dom.Document
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.nio.file.Files
import java.nio.file.Path

val objectMapper: ObjectMapper = ObjectMapper()
    .registerKotlinModule()

val colorProfile: ByteArray = IOUtils.toByteArray(Application::class.java.getResourceAsStream("/sRGB2014.icc"))

val fonts: Array<FontMetadata> =
    objectMapper.readValue(
        Files.newInputStream(Path.of(Application::class.java.getResource("/fonts/config.json").toURI()))
    )

data class FontMetadata(
    val family: String,
    val path: String,
    val weight: Int,
    val style: BaseRendererBuilder.FontStyle,
    val subset: Boolean
) {
    val bytes: ByteArray = Files.readAllBytes(Path.of(Application::class.java.getResource("/fonts/${path}").toURI()))
}

@Service
class PDFGenService {

    fun getPDFAsByteArray(json: String): ByteArray {
        val list = jacksonObjectMapper().readValue(json, List::class.java)
        val doc = getHTMLDocument(list)
        val os = ByteArrayOutputStream()
        createPDFA(doc, os)
        return os.toByteArray()
    }

    private fun getHTMLDocument(list: List<*>): Document {
        val c = HtmlCreator(list)
        return c.getDoc()
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