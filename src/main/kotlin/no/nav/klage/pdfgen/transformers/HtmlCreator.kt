package no.nav.klage.pdfgen.transformers

import kotlinx.html.*
import kotlinx.html.dom.append
import kotlinx.html.dom.create
import kotlinx.html.dom.createHTMLDocument
import no.nav.klage.pdfgen.transformers.ElementType.*
import org.w3c.dom.Document
import org.w3c.dom.Node

@Suppress("UNCHECKED_CAST")
class HtmlCreator(val dataList: List<*>) {

    private val document: Document = createHTMLDocument()
        .html {
            head {
                style {
                    unsafe {
                        raw(
                            """
                                * {
                                    font-family: "Source Sans Pro" !important;
                                }
                                .bold {
                                    font-weight: bold
                                }
                                .underline {
                                    text-decoration: underline;
                                }
                                .italic {
                                    font-style: italic
                                }
                            """.trimIndent()
                        )
                    }
                }
            }
            body {
                div { id = "div_content_id" }
            }
        }

    private fun addStaticSimpleElement(map: Map<String, *>) {
        val label = map["label"] ?: throw RuntimeException("no content here")
        val text = map["content"] ?: throw RuntimeException("no content here")

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

    private fun Tag.addHTMLElement(map: Map<String, *>) {
        when (map["type"]) {
            "paragraph", "ul", "ol", "li", "table", "tr", "td" -> elementWithPossiblyMultibleChildren(map)
            "blockquote", "h1", "h2", "standard-text" -> elementWithOnlyOneChild(map)
        }
    }

    private fun Tag.elementWithOnlyOneChild(map: Map<String, *>) {
        val onlyChild = (map["children"] as List<Map<String, *>>).first()
        val element = when (map["type"]) {
            "blockquote" -> BLOCKQUOTE(initialAttributes = emptyMap(), consumer = this.consumer)
            "h1" -> H1(initialAttributes = emptyMap(), consumer = this.consumer)
            "h2" -> H2(initialAttributes = emptyMap(), consumer = this.consumer)
            "standard-text" -> SPAN(initialAttributes = emptyMap(), consumer = this.consumer)
            else -> throw RuntimeException("what happened?")
        }

        element.visit {
            +onlyChild["text"].toString()
        }
    }

    private fun Tag.elementWithPossiblyMultibleChildren(map: Map<String, *>) {
        val children = map["children"] as List<Map<String, *>>

        val element = when (map["type"]) {
            "paragraph" -> P(initialAttributes = emptyMap(), consumer = this.consumer)
            "ul" -> UL(initialAttributes = emptyMap(), consumer = this.consumer)
            "ol" -> OL(initialAttributes = emptyMap(), consumer = this.consumer)
            "li" -> LI(initialAttributes = emptyMap(), consumer = this.consumer)
            "table" -> TABLE(initialAttributes = emptyMap(), consumer = this.consumer)
            "tr" -> TR(initialAttributes = emptyMap(), consumer = this.consumer)
            "td" -> TD(initialAttributes = emptyMap(), consumer = this.consumer)
            else -> throw RuntimeException("what happened?")
        }
        element.visit {
            children.forEach {
                when (it.getType()) {
                    LEAF -> this.addLeafElement(it)
                    ELEMENT -> this.addHTMLElement(it)
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
                this.addHTMLElement(it)
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
                when (type) {
                    "text" -> return STATIC_SIMPLE_ELEMENT
                    "rich-text" -> return STATIC_RICH_TEXT_ELEMENT
                    "paragraph", "h1", "h2", "ul", "ol", "li",
                    "table", "tr", "td", "blockquote", "standard-text" -> return ELEMENT
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
