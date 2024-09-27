package no.nav.klage.pdfgen.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import kotlinx.html.emptyMap
import no.nav.klage.pdfgen.api.view.DocumentToPdfRequest
import no.nav.klage.pdfgen.transformers.HtmlCreator
import no.nav.klage.pdfgen.util.createPDFA
import org.springframework.stereotype.Service
import org.w3c.dom.Document
import java.io.ByteArrayOutputStream

@Service
class PDFGenService {

    fun getPDFAsByteArray(data: DocumentToPdfRequest): ByteArray {
        val doc = getHTMLDocument(data)
        val os = ByteArrayOutputStream()
        createPDFA(doc, os)
        return os.toByteArray()
    }

    fun validateDocumentContent(data: DocumentToPdfRequest) {
        getHTMLDocument(data, true)
    }

    private fun getHTMLDocument(data: DocumentToPdfRequest, validationMode: Boolean = false): Document {
        validateHeaderFooter(data)
        val c = HtmlCreator(data, validationMode)
        return c.getDoc()
    }

    private fun validateHeaderFooter(data: DocumentToPdfRequest) {
        val list = jacksonObjectMapper().readValue(data.json, List::class.java) as List<Map<String, *>>

        if (list.any { it["type"] == "header" }.xor(list.any { it["type"] == "footer" })) {
            throw RuntimeException("Both a header and a footer must be defined.")
        }
    }
}