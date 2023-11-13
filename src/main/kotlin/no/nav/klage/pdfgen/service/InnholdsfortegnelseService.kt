package no.nav.klage.pdfgen.service

import kotlinx.html.*
import kotlinx.html.dom.create
import kotlinx.html.dom.createHTMLDocument
import kotlinx.html.dom.serialize
import no.nav.klage.pdfgen.api.view.InnholdsfortegnelseRequest
import no.nav.klage.pdfgen.api.view.Type
import no.nav.klage.pdfgen.transformers.getVedleggsoversiktCss
import no.nav.klage.pdfgen.util.createPDFA
import org.springframework.stereotype.Service
import org.w3c.dom.Document
import java.io.ByteArrayOutputStream
import java.time.format.DateTimeFormatter
import java.time.ZoneId
import java.util.Locale

@Service
class InnholdsfortegnelseService {

    fun getInnholdsfortegnelsePDFAsByteArray(input: InnholdsfortegnelseRequest): ByteArray {
        val doc = getHTMLDocument(input)
        val os = ByteArrayOutputStream()
        createPDFA(doc, os)
        return os.toByteArray()
    }

    private val DATE_FORMAT = DateTimeFormatter.ofPattern("dd. MMM yyyy", Locale("nb", "NO")).withZone(ZoneId.of("Europe/Oslo"))

    private fun getHTMLDocument(input: InnholdsfortegnelseRequest): Document {
        var counter = 1
        val totalCount = input.documents.size

        val document = createHTMLDocument()
            .html {
                head {
                    style {
                        unsafe {
                            raw(
                                getVedleggsoversiktCss("")
                            )
                        }
                    }
                }
                body {
                    id = "body"
                    h1 { +"Vedleggsliste" }

                    if (input.documents.isNotEmpty()) {
                        table {
                            thead {
                                tr {
                                    th { 
                                        classes = setOf("white-space-no-wrap")
                                        +"Nr."
                                    }
                                    th {
                                        classes = setOf("white-space-no-wrap")
                                         +"Dato"
                                    }
                                    th { +"Tittel" }

                                }
                            }
                            
                            tbody {
                                input.documents.forEachIndexed { index, it ->
                                    tr {
                                        classes = setOf(if (index % 2 == 0) "even" else "odd")
                                        
                                        td { 
                                          classes = setOf("white-space-no-wrap")
                                          +"${counter++} av $totalCount" 
                                        }
                                        td { 
                                            classes = setOf("white-space-no-wrap")
                                            +it.dato.format(DATE_FORMAT)
                                        }
                                        td { +it.tittel }
    
                                    }
                                    
                                    tr {
                                        classes = setOf("extra-row", if (index % 2 == 0) "even" else "odd")
                                        td { 
                                            colSpan = "3"

                                            div {
                                                label { +"Saksnr.: "}
                                                span { 
                                                    classes = setOf("saksnummer")
                                                    +it.saksnummer
                                                 }
                                            }
                                            
                                            when(it.type) {
                                                Type.I -> div {
                                                    div {
                                                        label {+"Avsender: "}
                                                        span {+it.avsenderMottaker}
                                                    }
                                                    div {
                                                        label {+"Mottaker: "}
                                                        span {+"NAV"}
                                                    }
                                                }
                                                Type.U -> div {
                                                    div {
                                                        label {+"Avsender: "}
                                                        span {+"NAV"}
                                                    }
                                                    div {
                                                        label {+"Mottaker: "}
                                                        span {+it.avsenderMottaker}
                                                    }
                                                }
                                                Type.N -> div {+"Notat"; classes = setOf("bold")}
                                            }

                                            div {
                                                label { +"Tema: "}
                                                span { +it.tema }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

       println(document.serialize())

        return document
    }
}