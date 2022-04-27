package no.nav.klage.pdfgen.transformers

import kotlinx.html.*
import kotlinx.html.dom.append
import kotlinx.html.dom.create
import kotlinx.html.dom.createHTMLDocument
import kotlinx.html.dom.serialize
import no.nav.klage.pdfgen.transformers.ElementType.*
import no.nav.klage.pdfgen.util.getLogger
import no.nav.klage.pdfgen.util.getSecureLogger
import org.w3c.dom.Document
import org.w3c.dom.Node
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@Suppress("UNCHECKED_CAST")
class HtmlCreator(val dataList: List<*>) {

    companion object {
        @Suppress("JAVA_CLASS_ON_COMPANION")
        private val logger = getLogger(javaClass.enclosingClass)
        private val secureLogger = getSecureLogger()
    }

    private val document: Document = createHTMLDocument()
        .html {
            head {
                style {
                    unsafe {
                        raw(
                            """
                                html {
                                    white-space: pre-wrap;
                                    font-family: "Source Sans Pro" !important;
                                    box-sizing: border-box;
                                }
                                *, ::before, ::after {
                                  box-sizing: inherit;
                                }
                                .column {
                                  font-size: 16px;
                                  display: inline-block;
                                  width: 50%;
                                }
                                /* Fjerner spacing mellom inline-blockene */
                                .wrapper {
                                  font-size: 0;
                                }
                                h1 * {
                                    font-size: 16pt;
                                }
                                h2 * {
                                    font-size: 14pt;
                                }
                                h3 * {
                                    font-size: 12pt;
                                }
                                #header span {
                                    font-size: 10pt;
                                }
                                img {
                                    display: block;
                                    width: 100pt;
                                    float: right;
                                },
                                p, span {
                                    font-size: 12pt;
                                }
                                .bold {
                                    font-weight: bold;
                                }
                                .underline {
                                    text-decoration: underline;
                                }
                                .italic {
                                    font-style: italic;
                                }
                                .alignRight {
                                    text-align: right;
                                }
                                .pageBreak {
                                    page-break-after: always;
                                }
                                
                                @page {
                                    margin: 15mm 20mm 20mm 20mm;
                                    @bottom-left {
                                        content: "";
                                    }
                                    @bottom-right {
                                        font-family: "Source Sans Pro" !important;
                                        font-size: 10pt;
                                        content: "Side " counter(page) " av " counter(pages);
                                    }
                                }

                                @page :first {
                                    margin: 15mm 20mm 30mm 20mm;
                                    @bottom-left {
                                        font-family: "Source Sans Pro" !important;
                                        font-size: 10pt;
                                        content: "Postadresse: NAV Klageinstans Oslo og Akershus // Postboks 7028 St. Olavs plass // 0130 OSLO\ATelefon: 21 07 17 30\Anav.no";
                                        white-space: pre-wrap;
                                    }
                                    @bottom-right {
                                        content: "";
                                    }
                                }
                            """.trimIndent()
                        )
                    }
                }
            }
            body {
                div {
                    id = "header"
                    span { +"Returadresse," }
                    br { }
                    span { +"NAV Klageinstans Oslo og Akershus, Postboks 7028 St. Olavs plass, 0130 OSLO" }
                    img { src = "nav_logo.png" }
                }
                br { }
                br { }
                br { }
                br { }

                div { id = "div_content_id" }
            }
        }

    private fun addLabelContentElement(map: Map<String, *>) {
        val result = map["result"] ?: return

        val divElement = document.getElementById("div_content_id") as Node
        divElement.append {
            div {
                p { +"$result" }
            }
        }
    }

    private fun addMaltekst(map: Map<String, *>) {
        val elementList = map["maltekst"]
        if (elementList != null) {
            elementList as List<Map<String, *>>
        } else {
            return
        }
        elementList.forEach {
            val div = document.create.div {
                this.addElementWithPossiblyChildren(it)
            }
            val divElement = document.getElementById("div_content_id") as Node
            divElement.appendChild(div)
        }
    }

    private fun addElementWithPossiblyChildren(map: Map<String, *>) {
        val div = document.create.div {
            this.addElementWithPossiblyChildren(map)
        }
        val divElement = document.getElementById("div_content_id") as Node
        divElement.appendChild(div)
    }

    private fun Tag.addElementWithPossiblyChildren(map: Map<String, *>) {
        val elementType = map["type"]
        var children = emptyList<Map<String, *>>()

        val applyClasses = if (map["textAlign"] == "text-align-right") mutableSetOf("alignRight") else mutableSetOf()

        if (elementType != "page-break") {
            children = map["children"] as List<Map<String, *>>
        } else {
            applyClasses.add("pageBreak")
        }

        val element = when (elementType) {
            "standard-text" -> SPAN(initialAttributes = emptyMap(), consumer = this.consumer)
            "heading-one" -> H1(initialAttributes = emptyMap(), consumer = this.consumer)
            "heading-two" -> H2(initialAttributes = emptyMap(), consumer = this.consumer)
            "heading-three" -> H3(initialAttributes = emptyMap(), consumer = this.consumer)
            "blockquote" -> BLOCKQUOTE(initialAttributes = emptyMap(), consumer = this.consumer)
            "paragraph" -> P(initialAttributes = emptyMap(), consumer = this.consumer)
            "bullet-list" -> UL(initialAttributes = emptyMap(), consumer = this.consumer)
            "numbered-list" -> OL(initialAttributes = emptyMap(), consumer = this.consumer)
            "list-item" -> LI(initialAttributes = emptyMap(), consumer = this.consumer)
            "table" -> TABLE(initialAttributes = emptyMap(), consumer = this.consumer)
            "table-row" -> TR(initialAttributes = emptyMap(), consumer = this.consumer)
            "table-cell" -> TD(initialAttributes = emptyMap(), consumer = this.consumer)
            "page-break", "list-item-container" -> DIV(initialAttributes = emptyMap(), consumer = this.consumer)
            else -> throw RuntimeException("unknown element type: " + map["type"])
        }

        element.visit {
            classes = applyClasses
            children.forEach {
                when (it.getType()) {
                    LEAF -> this.addLeafElement(it)
                    ELEMENT -> this.addElementWithPossiblyChildren(it)
                    else -> {}
                }
            }
        }
    }

    private fun addDocumentList(map: Map<String, *>) {
        val children = map["documents"] as List<Map<String, String>>
        val dElement = document.create.div {
            ul {
                children.forEach {
                    li { +it["title"].toString() }
                }
            }
        }
        val divElement = document.getElementById("div_content_id") as Node
        divElement.appendChild(dElement)
    }

    private fun addSignatureElement(map: Map<String, *>) {
        val dElement = document.create.div {
            classes = setOf("wrapper")
            if (map.containsKey("medunderskriver")) {
                val medunderskriver = map["medunderskriver"] as Map<String, Map<String, *>>
                div {
                    classes = setOf("column")
                    div { +medunderskriver["name"].toString() }
                    div { +medunderskriver["title"].toString() }
                }
            }
            if (map.containsKey("saksbehandler")) {
                val saksbehandler = map["saksbehandler"] as Map<String, Map<String, *>>
                div {
                    classes = setOf("column")
                    div { +saksbehandler["name"].toString() }
                    div { +saksbehandler["title"].toString() }
                }
            }

        }
        val divElement = document.getElementById("div_content_id") as Node
        divElement.appendChild(dElement)
    }

    private fun Tag.addLeafElement(map: Map<String, *>) {
        val text = map["text"] ?: throw RuntimeException("no content here")

        val classesToAdd = mutableSetOf<String>()
        if (map["bold"] == true) {
            classesToAdd += "bold"
        }
        if (map["underline"] == true) {
            classesToAdd += "underline"
        }
        if (map["italic"] == true) {
            classesToAdd += "italic"
        }

        this.consumer.span {
            classes = classesToAdd
            +text.toString()
        }
    }

    private fun addCurrentDate() {
        val formatter = DateTimeFormatter.ofPattern("dd. MMMM yyyy", Locale.forLanguageTag("nb"))
        val dateAsText = ZonedDateTime.now(ZoneId.of("Europe/Oslo")).format(formatter)

        val div = document.create.div {
            classes = setOf("alignRight")
            +"Dato: $dateAsText"
        }
        val divElement = document.getElementById("div_content_id") as Node
        divElement.appendChild(div)

    }

    fun getDoc(): Document {
        dataList.forEach {
            processElement(it as Map<String, *>)
        }
        secureLogger.debug(document.serialize())
        return document
    }

    private fun processElement(map: Map<String, *>) {
        when (map.getType()) {
            LABEL_CONTENT_ELEMENT -> addLabelContentElement(map)
            SIGNATURE_ELEMENT -> addSignatureElement(map)
            ELEMENT -> addElementWithPossiblyChildren(map)
            DOCUMENT_LIST -> addDocumentList(map)
            MALTEKST -> addMaltekst(map)
            CURRENT_DATE -> addCurrentDate()
            LEAF -> {}
        }
    }

    private fun Map<String, *>.getType(): ElementType {
        val type = this["type"]
        if (type != null) {
            return when (type) {
                "label-content" -> LABEL_CONTENT_ELEMENT
                "signature" -> SIGNATURE_ELEMENT
                "document-list" -> DOCUMENT_LIST
                "maltekst" -> MALTEKST
                "current-date" -> CURRENT_DATE
                else -> ELEMENT
            }
        }
        return LEAF
    }
}

enum class ElementType {
    LABEL_CONTENT_ELEMENT,
    SIGNATURE_ELEMENT,
    ELEMENT,
    LEAF,
    DOCUMENT_LIST,
    MALTEKST,
    CURRENT_DATE,
}
