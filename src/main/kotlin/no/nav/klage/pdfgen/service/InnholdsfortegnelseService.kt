package no.nav.klage.pdfgen.service

import kotlinx.html.*
import kotlinx.html.dom.create
import kotlinx.html.dom.createHTMLDocument
import kotlinx.html.dom.serialize
import no.nav.klage.pdfgen.api.view.InnholdsfortegnelseRequest
import no.nav.klage.pdfgen.transformers.getCss
import no.nav.klage.pdfgen.util.createPDFA
import org.springframework.stereotype.Service
import org.w3c.dom.Document
import java.io.ByteArrayOutputStream

@Service
class InnholdsfortegnelseService {

    fun getInnholdsfortegnelsePDFAsByteArray(input: InnholdsfortegnelseRequest): ByteArray {
        val doc = getHTMLDocument(input)
        val os = ByteArrayOutputStream()
        createPDFA(doc, os)
        return os.toByteArray()
    }

    private fun getHTMLDocument(input: InnholdsfortegnelseRequest): Document {
        var counter = 1
        val totalCount = input.documents.size

        val document = createHTMLDocument()
            .html {
                head {
                    style {
                        unsafe {
                            raw(
                                getCss("")
                            )
                        }
                    }
                }
                body {
                    id = "body"
                    h1 { +"Innholdsfortegnelse" }

                    if (input.documents.isNotEmpty()) {
                        table {
                            thead {
                                tr {
                                    th { +"Nummer" }
                                    th { +"Dato" }
                                    th { +"Tema" }
                                    th { +"Saksnummer" }
                                    th { +"Avsender/Mottaker" }
                                    th { +"Tittel" }
                                }
                            }
                            input.documents.forEach {
                                tr {
                                    td { +"${counter++} av $totalCount" }
                                    td { +it.dato.toString() }
                                    td { +it.tema }
                                    td { +it.saksnummer }
                                    td { +it.avsenderMottaker }
                                    td { +it.tittel }
                                }
                            }
                        }
                    }
                }
            }

//        println(document.serialize())

        return document
    }
}