package no.nav.klage.pdfgen.service

import kotlinx.html.*
import kotlinx.html.dom.createHTMLDocument
import no.nav.klage.pdfgen.api.view.SvarbrevRequest
import no.nav.klage.pdfgen.transformers.getCss
import no.nav.klage.pdfgen.util.createPDFA
import no.nav.klage.pdfgen.util.getFormattedDate
import org.springframework.stereotype.Service
import org.w3c.dom.Document
import java.io.ByteArrayOutputStream
import java.time.LocalDate

@Service
class SvarbrevService {

    fun getSvarbrevAsByteArray(svarbrevRequest: SvarbrevRequest): ByteArray {
        val doc = getHTMLDocument(svarbrevRequest)
        val os = ByteArrayOutputStream()
        createPDFA(doc, os)
        return os.toByteArray()
    }

    private fun getHTMLDocument(svarbrevRequest: SvarbrevRequest): Document {
        return createHTMLDocument()
            .html {
                head {
                    style {
                        unsafe {
                            raw(
                                getCss("footer linje 1 \\A footer linje 2")
                            )
                        }
                    }
                    title(svarbrevRequest.title)
                }
                body {
                    id = "body"
                    header {
                        div {
                            id = "header_text"
                            +"Returadresse,"
                            br { }
                            +"Her må BE finne ut hva det skal stå"
                        }
                        div {
                            id = "logo"
                            img { src = "nav_logo.png" }
                        }
                    }
                    div {
                        classes = setOf("current-date")
                        +"Dato: ${getFormattedDate(LocalDate.now())}"
                     }
                    h1 { +"NAV orienterer om saksbehandlingen av anken din om ${svarbrevRequest.ytelsenavn.toSpecialCase()}" }
                    p {
                        div {
                            span {
                                classes = setOf("bold")
                                +"Saken gjelder: "
                            }
                             +svarbrevRequest.sakenGjelder.name
                            }
                        div {
                            span {
                                classes = setOf("bold")
                                +"Fødselsnummer: "
                            }
                            +svarbrevRequest.sakenGjelder.fnr.toFnrView()
                        }
                        

                        if (svarbrevRequest.klager != null && svarbrevRequest.klager.fnr != svarbrevRequest.sakenGjelder.fnr) {
                            div {
                                span {
                                    classes = setOf("bold")
                                    +"Den ankende part: "
                                }
                                +svarbrevRequest.klager.name
                            }
                        }
                        br { }
                        if (svarbrevRequest.fullmektigFritekst != null) {
                            +"Fullmektig: ${svarbrevRequest.fullmektigFritekst}"
                            br { }
                        }
                    }

                    p {
                        +"Vi viser til anken din, som vi mottok ${getFormattedDate(svarbrevRequest.ankeReceivedDate)}."
                    }

                    h2 { +"Behandlingen av ankesaken" }
                    p {
                        +"Saksbehandlingstiden vår er nå "
                        span { +svarbrevRequest.behandlingstidInWeeks.toString() }
                        +" uker. Du finner oversikt over saksbehandlingstidene våre på www.nav.no."
                    }
                    p {
                        +"Dersom vi ikke endrer vedtaket du har fått, sender vi saken din til Trygderetten."
                    }
                    h2 { +"Dersom saken går til Trygderetten" }
                    p {
                        +"Hvis saken din går videre til Trygderetten, vil du få kopi av oversendelsesbrevet, der vi forklarer saken og begrunnelsen for vedtaket vårt."
                    }
                    p {
                        +"Du får da mulighet til å komme med merknader, som vil følge saken til Trygderetten."
                    }
                    h2 { +"Du må melde fra om endringer" }
                    p {
                        +"Vi ber deg holde oss orientert om forhold som kan ha betydning for avgjørelsen av saken din. Det vil si endringer i for eksempel i medisinske forhold, arbeid, inntekt, sivilstand og lignende."
                    }
                    p {
                        +"Du finner første side for innsending av dokumenter på www.nav.no/klage. Velg "
                        span { +svarbrevRequest.enhetsnavn }
                        +"."
                    }
                    h2 { +"Du har rett til innsyn" }
                    p {
                        +"Du har rett til å se dokumentene i saken din."
                    }
                    h2 { +"Informasjon om fri rettshjelp" }
                    p {
                        +"Dette får du vite mer om hos Statsforvalteren eller advokat."
                    }

                    div {
                        classes = setOf("signature")
                        +"Med hilsen"
                        br { }
                        +"NAV Klageinstans"
                    }

                    

                }

            }
    }

    private fun String.toSpecialCase(): String {
        val strings = this.split(" - ")
        return if (strings.size == 2) {
            strings[0].decapitalize() + " - " + strings[1].decapitalize()
        } else this
    }

    private fun String.decapitalize(): String {
        return if (!this.startsWith("NAV")) {
            this.replaceFirstChar(Char::lowercase)
        } else this
    }

    private fun String.toFnrView() = this.substring(0, 6) + " " + this.substring(6)
}