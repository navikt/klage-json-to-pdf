package no.nav.klage.pdfgen.transformers

import kotlinx.html.*
import kotlinx.html.dom.append
import kotlinx.html.dom.create
import kotlinx.html.dom.createHTMLDocument
import kotlinx.html.dom.serialize
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
                "StaticRichTextElement" -> addStaticRichElement(it)
            }
        }
    }

    private fun addElement(map: Map<String, *>) {
        when (map["type"]) {
            "paragraph" -> {
                val children = map["children"] as List<Map<String, *>>

                val divElement = document.getElementById("div_content_id") as Node

                val pElement = document.create.p {
                    children.forEach {
                        when (it.getType()) {
                            "Leaf" -> this.addLeafElement(it)
                        }
                    }
                }
                divElement.appendChild(pElement)
            }
        }
    }

    private fun HTMLTag.addHTMLElement(map: Map<String, *>) {
        when (map["type"]) {
            "paragraph" -> p(map)
            "ul" -> ul(map)
            "ol" -> ol(map)
            "li" -> li(map)
            "h1" -> h1(map)
            "h2" -> h2(map)
            "h3" -> h3(map)
            "table" -> table(map)
            "tr" -> tr(map)
            "td" -> td(map)
            "blockquote" -> blockquote(map)
            "standard-text" -> standardText(map)
        }
    }

    private fun HTMLTag.blockquote(map: Map<String, *>) {
        val onlyChild = (map["children"] as List<Map<String, *>>).first()
        this.consumer.blockQuote {
            +onlyChild["text"].toString()
        }
    }

    private fun HTMLTag.standardText(map: Map<String, *>) {
        val onlyChild = (map["children"] as List<Map<String, *>>).first()
        this.consumer.span {
            +onlyChild["text"].toString()
        }
    }

    private fun HTMLTag.h1(map: Map<String, *>) {
        val onlyChild = (map["children"] as List<Map<String, *>>).first()
        this.consumer.h1 {
            +onlyChild["text"].toString()
        }
    }

    private fun HTMLTag.h2(map: Map<String, *>) {
        val onlyChild = (map["children"] as List<Map<String, *>>).first()
        this.consumer.h2 {
            +onlyChild["text"].toString()
        }
    }

    private fun HTMLTag.h3(map: Map<String, *>) {
        val onlyChild = (map["children"] as List<Map<String, *>>).first()
        this.consumer.h3 {
            +onlyChild["text"].toString()
        }
    }

    private fun HTMLTag.li(map: Map<String, *>) {
        val children = map["children"] as List<Map<String, *>>
        this.consumer.li {
            children.forEach {
                when (it.getType()) {
                    "Leaf" -> this.addLeafElement(it)
                    "Element" -> this.addHTMLElement(it)
                }
            }
        }
    }

    private fun HTMLTag.table(map: Map<String, *>) {
        val children = map["children"] as List<Map<String, *>>
        this.consumer.table {
            children.forEach {
                when (it.getType()) {
                    "Leaf" -> this.addLeafElement(it)
                    "Element" -> this.addHTMLElement(it)
                }
            }
        }
    }

    private fun HTMLTag.tr(map: Map<String, *>) {
        val children = map["children"] as List<Map<String, *>>
        this.consumer.tr {
            children.forEach {
                when (it.getType()) {
                    "Leaf" -> this.addLeafElement(it)
                    "Element" -> this.addHTMLElement(it)
                }
            }
        }
    }

    private fun HTMLTag.td(map: Map<String, *>) {
        val children = map["children"] as List<Map<String, *>>
        this.consumer.td {
            println(this::class.java.name)
            children.forEach {
                when (it.getType()) {
                    "Leaf" -> this.addLeafElement(it)
                    "Element" -> this.addHTMLElement(it)
                }
            }
        }
    }

    private fun HTMLTag.ul(map: Map<String, *>) {
        val children = map["children"] as List<Map<String, *>>
        this.consumer.ul {
            children.forEach {
                when (it.getType()) {
                    "Leaf" -> this.addLeafElement(it)
                    "Element" -> this.addHTMLElement(it)
                }
            }
        }
    }

    private fun HTMLTag.ol(map: Map<String, *>) {
        val children = map["children"] as List<Map<String, *>>
        this.consumer.ol {
            children.forEach {
                when (it.getType()) {
                    "Leaf" -> this.addLeafElement(it)
                    "Element" -> this.addHTMLElement(it)
                }
            }
        }
    }

    private fun HTMLTag.p(map: Map<String, *>) {
        val children = map["children"] as List<Map<String, *>>
        this.consumer.p {
            children.forEach {
                when (it.getType()) {
                    "Leaf" -> this.addLeafElement(it)
                    "Element" -> this.addHTMLElement(it)
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

    private fun HTMLTag.addLeafElement(map: Map<String, *>) {
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
        println(document.serialize())
        return document
    }

    private fun processElement(map: Map<String, *>) {
        when (map.getType()) {
            "TemplateSection" -> addTemplateSection(map)
            "Element" -> addElement(map)
            "StaticSimpleElement" -> addStaticSimpleElement(map)
            "StaticRichTextElement" -> addStaticRichElement(map)
        }
    }

    private fun Map<String, *>.getType(): String {
        if (this.containsKey("title")) {
            return "TemplateSection"
        } else {
            val type = this["type"]
            if (type != null) {
                when (type) {
                    "text" -> return "StaticSimpleElement"
                    "rich-text" -> return "StaticRichTextElement"
                    "paragraph", "h1", "h2", "h3", "ul", "ol", "li", "table", "tr", "td", "blockquote", "standard-text" -> return "Element"
                }
            }
        }
        return "Leaf"
    }
}


