package no.nav.klage.pdfgen.service

import kotlinx.html.*
import kotlinx.html.dom.createHTMLDocument
import no.nav.klage.pdfgen.transformers.getVedleggsoversiktCss
import no.nav.klage.pdfgen.util.createPDFA
import org.springframework.stereotype.Service
import org.w3c.dom.Document
import java.io.ByteArrayOutputStream

@Service
class SvarbrevService {

    fun getSvarbrevAsByteArray(): ByteArray {
        val doc = getHTMLDocument()
        val os = ByteArrayOutputStream()
        createPDFA(doc, os)
        return os.toByteArray()
    }

    private fun getHTMLDocument(): Document {

        return createHTMLDocument()
            .html {
                head {
                    style {
                        unsafe {
                            raw(
                                getVedleggsoversiktCss()
                            )
                        }
                    }
                }
                body {
                    id = "body"
                    h1 { +"NAV orienterer om saksbehandlingen av anken din om ytelse" }
                }
            }

    }
}