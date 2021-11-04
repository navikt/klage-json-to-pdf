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
                                #header {
                                    font-size: 14px;
                                }
                                img {
                                    width: 100px;
                                    float: right;
                                }
                                * {
                                    font-family: "Source Sans Pro" !important;
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
                            """.trimIndent()
                        )
                    }
                }
            }
            body {
                div { id = "header"
                    span { +"Returadresse," }
                    br {  }
                    span { +"NAV Klageinstans Oslo og Akershus, Postboks 7028 St. Olavs plass, 0130 OSLO" }
                    img { src = "nav_logo.png" }
                }
                br {  }
                br {  }

                div { id = "div_content_id" }
            }
        }

    private fun addStaticSimpleElement(map: Map<String, *>) {
        val label = map["label"] ?: throw RuntimeException("no content here")
        val text = map["content"] ?: "missing content"//throw RuntimeException("no content here")

        val divElement = document.getElementById("div_content_id") as Node
        divElement.append {
            div {
                +label.toString()
                br { }
                +text.toString()
                br { }
                br { }
            }
        }
    }

    private fun addTemplateSection(map: Map<String, *>) {
        val h1 = document.create.h1 {
            +map["title"].toString()
        }
        val divElement = document.getElementById("div_content_id") as Node
        divElement.appendChild(h1)

        val children = map["content"] as List<Map<String, *>>

        children.forEach {
            when (it.getType()) {
                STATIC_RICH_TEXT_ELEMENT -> addStaticRichElement(it)
                STATIC_SIMPLE_ELEMENT -> addStaticSimpleElement(it)
            }
        }
    }

    private fun Tag.addElementWithPossiblyMultipleChildren(map: Map<String, *>) {
        val children = map["children"] as List<Map<String, *>>

        val element = when (map["type"]) {
            "standard-text" -> SPAN(initialAttributes = emptyMap(), consumer = this.consumer)
            "heading-one" -> H1(initialAttributes = emptyMap(), consumer = this.consumer)
            "heading-two" -> H2(initialAttributes = emptyMap(), consumer = this.consumer)
            "quote", "blockquote" -> BLOCKQUOTE(initialAttributes = emptyMap(), consumer = this.consumer)
            "paragraph" -> P(initialAttributes = emptyMap(), consumer = this.consumer)
            "bullet-list" -> UL(initialAttributes = emptyMap(), consumer = this.consumer)
            "numbered-list" -> OL(initialAttributes = emptyMap(), consumer = this.consumer)
            "list-item" -> LI(initialAttributes = emptyMap(), consumer = this.consumer)
            "table" -> TABLE(initialAttributes = emptyMap(), consumer = this.consumer)
            "table-row" -> TR(initialAttributes = emptyMap(), consumer = this.consumer)
            "table-cell" -> TD(initialAttributes = emptyMap(), consumer = this.consumer)
            else -> throw RuntimeException("unknown element type: " + map["type"])
        }

        val applyClasses = if (map["textAlign"] == "text-align-right") setOf("alignRight") else emptySet()

        element.visit {
            classes = applyClasses
            children.forEach {
                when (it.getType()) {
                    LEAF -> this.addLeafElement(it)
                    ELEMENT -> this.addElementWithPossiblyMultipleChildren(it)
                }
            }
        }
    }

    private fun addStaticRichElement(map: Map<String, *>) {
        val children = map["content"] as List<Map<String, *>>
        val label = map["label"].toString()

        val dElement = document.create.div {
            span {
                +label
                br { }
            }
            children.forEach {
                this.addElementWithPossiblyMultipleChildren(it)
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

    fun getDoc(): Document {
        dataList.forEach {
            processElement(it as Map<String, *>)
        }
        secureLogger.debug(document.serialize())
        return document
    }

    private fun processElement(map: Map<String, *>) {
        when (map.getType()) {
            TEMPLATE_SECTION -> addTemplateSection(map)
            STATIC_SIMPLE_ELEMENT -> addStaticSimpleElement(map)
            STATIC_RICH_TEXT_ELEMENT -> addStaticRichElement(map)
        }
    }

    private fun Map<String, *>.getType(): ElementType {
        if (this.containsKey("title")) {
            return TEMPLATE_SECTION
        } else {
            val type = this["type"]
            if (type != null) {
                return when (type) {
                    "text" -> STATIC_SIMPLE_ELEMENT
                    "rich-text" -> STATIC_RICH_TEXT_ELEMENT
                    else -> ELEMENT
                }
            }
            return LEAF
        }
    }
}

enum class ElementType {
    TEMPLATE_SECTION,
    STATIC_SIMPLE_ELEMENT,
    STATIC_RICH_TEXT_ELEMENT,
    ELEMENT,
    LEAF
}
