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
        val totalCount = input.dokumenterUnderArbeid.size + input.journalfoerteDokumenter.size

        val document = createHTMLDocument()
            .html {
                body {
                    id = "body"
                    h1 { +"Innholdsfortegnelse" }

                    if (input.dokumenterUnderArbeid.isNotEmpty()) {
                        h3 { +"Ikke (enda) journalførte dokumenter" }

                        table {
                            thead {
                                tr {
                                    th { +"Nummer" }
                                    th { +"Tittel" }
                                    th { +"Tema" }
                                    th { +"Dato" }
                                    th { +"Mottaker" }
                                    th { +"Saksnummer" }
                                    th { +"Type" }
                                }
                            }
                            input.dokumenterUnderArbeid.forEach {
                                tr {
                                    td { +"${counter++} av $totalCount" }
                                    td { +it.tittel }
                                    td { +it.tema }
                                    td { +it.dato.toString() }
                                    td { +it.avsenderMottaker }
                                    td { +it.saksnummer }
                                    td { +it.type }
                                }
                            }
                        }
                    }

                    if (input.journalfoerteDokumenter.isNotEmpty()) {
                        h3 { +"Journalførte dokumenter" }

                        table {
                            thead {
                                tr {
                                    th { +"Nummer" }
                                    th { +"Tittel" }
                                    th { +"Tema" }
                                    th { +"Dato" }
                                    th { +"Avsender/mottaker" }
                                    th { +"Saksnummer" }
                                    th { +"Type" }
                                }
                            }
                            input.journalfoerteDokumenter.forEach {
                                tr {
                                    td { +"${counter++} av $totalCount" }
                                    td { +it.tittel }
                                    td { +it.tema }
                                    td { +it.dato.toString() }
                                    td { +it.avsenderMottaker }
                                    td { +it.saksnummer }
                                    td { +it.type }
                                }
                            }
                        }
                    }
                }
            }
        //add css when we have a footer set
        val head = document.create.head {
            style {
                unsafe {
                    raw(
                        getCss("footer")
                    )
                }
            }
        }

        document.childNodes.item(0).insertBefore(head, document.childNodes.item(0).firstChild)

        println(document.serialize())

        return document
    }
}