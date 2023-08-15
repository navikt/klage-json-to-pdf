package no.nav.klage.pdfgen.transformers

import kotlinx.html.*
import kotlinx.html.dom.append
import kotlinx.html.dom.create
import kotlinx.html.dom.createHTMLDocument
import kotlinx.html.dom.serialize
import no.nav.klage.pdfgen.exception.EmptyPlaceholderException
import no.nav.klage.pdfgen.exception.EmptyRegelverkException
import no.nav.klage.pdfgen.transformers.ElementType.*
import no.nav.klage.pdfgen.transformers.ElementType.FOOTER
import no.nav.klage.pdfgen.transformers.ElementType.HEADER
import no.nav.klage.pdfgen.util.getLogger
import no.nav.klage.pdfgen.util.getSecureLogger
import org.w3c.dom.Document
import org.w3c.dom.Node
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@Suppress("UNCHECKED_CAST")
class HtmlCreator(val dataList: List<Map<String, *>>, val validationMode: Boolean = false) {

    companion object {
        @Suppress("JAVA_CLASS_ON_COMPANION")
        private val logger = getLogger(javaClass.enclosingClass)
        private val secureLogger = getSecureLogger()
    }

    private fun getCss(footer: String) =
        """
                                html {
                                    font-family: "Source Sans Pro" !important;
                                    box-sizing: border-box;
                                    font-weight: 400;
                                    letter-spacing: 0;
                                }
                                *, ::before, ::after {
                                  box-sizing: inherit;
                                  word-wrap: break-word;
                                  padding: 0;
                                  margin: 0;
                                }
                                .column {
                                  font-size: 12pt;
                                  display: inline-block;
                                  width: 50%;
                                }
                                /* Fjerner spacing mellom inline-blockene */
                                .wrapper {
                                  font-size: 0;
                                  page-break-before: avoid;
                                }
                                h1 {
                                    font-size: 16pt;
                                }
                                h2 {
                                    font-size: 14pt;
                                }
                                h3 {
                                    font-size: 12pt;
                                }
                                h1, h2, h3, h4, h5, h6 {
                                    font-weight: 600;
                                    margin-top: 1em;
                                    margin-bottom: 0;
                                    page-break-after: avoid;
                                }
                                .indent {
                                    padding-left: 24pt;
                                }
                                #header span {
                                    font-size: 10pt;
                                    white-space: pre;
                                }
                                img {
                                    display: block;
                                    width: 100pt;
                                    float: right;
                                }
                                p {
                                    font-size: 12pt;
                                    margin-top: 1em;
                                }
                                .placeholder-text {
                                    background-color: #EFA89D;
                                    border-radius: 3pt;
                                }
                                .bold {
                                    font-weight: 600;
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
                                .alignLeft {
                                    text-align: left;
                                }
                                .pageBreak {
                                    page-break-after: always;
                                }
                                table {
                                    border-spacing: 0;
                                    border-collapse: collapse;
                                    max-width: 100%;
                                    margin-top: 12pt;
                                    margin-bottom: 12pt;
                                    page-break-inside: avoid;
                                    -fs-border-rendering: no-bevel;
                                }
                                td {
                                    border: 1pt solid #8F8F8F;
                                    word-wrap: break-word;
                                    white-space: pre-wrap;
                                    vertical-align: top;
                                    text-align: left;
                                    background-color: transparent;
                                    padding: 4pt;
                                    padding-left: 3pt;
                                    padding-right: 3pt;
                                    min-width: 36pt;
                                }
                                tr:nth-child(odd) {
                                  background-color: rgb(247, 247, 247);
                                }
                                tr:nth-child(even) {
                                  background-color: #fff;
                                }
                                td > ul, td > ol {
                                  margin-top: 0;
                                }
                                td > * {
                                  margin-top: 0;
                                  margin-bottom: 6pt;
                                }

                                td > *:last-child {
                                  margin-bottom: 0;
                                }

                                td > span {
                                  margin-bottom: 0;
                                }
                                
                                ol, ul {
                                  padding-left: 12pt;
                                  margin: 0;
                                  margin-top: 12pt;
                                  page-break-inside: avoid;
                                }
                                
                                li > ul, li > ol {
                                  margin-top: 0;
                                }
                                
                                @page {
                                    margin: 20mm;
                                    margin-top: 15mm;
                                    size: a4;
                                    padding: 0;
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
                                    margin-bottom: 30mm;
                                    @bottom-left {
                                        font-family: "Source Sans Pro" !important;
                                        font-size: 10pt;
                                        content: "$footer";
                                        white-space: pre-wrap;
                                    }
                                    @bottom-right {
                                        content: "";
                                    }
                                }
                            """.trimIndent()


    private val document: Document = createHTMLDocument()
        .html {
            body {
                div {
                    id = "header"
                    span {
                        id = "header_text"
                        +"Returadresse:\nNAV Klageinstans"
                    }
                    img { src = "nav_logo.png" }
                }
                br { }
                br { }
                br { }
                br { }

                div { id = "div_content_id" }
            }
        }

    private var footer =
        "NAV Klageinstans\\Anav.no"

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
        addElements(map)
    }

    private fun addRegelverkContainer(map: Map<String, *>) {
        if (validationMode) {
            if (map["children"] == null || (map["children"] as List<Map<String, *>>).isEmpty()) {
                throw EmptyRegelverkException("Empty regelverk")
            } else if (getTexts(map).isEmpty()) {
                throw EmptyRegelverkException("Empty regelverk")
            }
        }

        addElements(map)
    }

    private fun getTexts(map: Map<String, *>): List<String> {
        val texts = mutableListOf<String>()

        if (map["text"] != null && (map["text"] as String).isNotBlank()) {
            texts += (map["text"] as String)
        }

        return if (map["children"] == null || (map["children"] as List<Map<String, *>>).isEmpty()) {
            texts
        } else {
            val children = (map["children"] as List<Map<String, *>>)
            children.forEach {
                texts.addAll(getTexts(it))
            }
            texts
        }
    }

    private fun addElements(map: Map<String, *>) {
        val elementList = map["children"]
        if (elementList != null) {
            elementList as List<Map<String, *>>
            elementList.forEach {
                val div = document.create.div {
                    this.addElementWithPossiblyChildren(map = it)
                }
                val divElement = document.getElementById("div_content_id") as Node
                divElement.appendChild(div)
            }
        } else {
            logger.error("No children element.")
            return
        }
    }

    private fun addRegelverk(map: Map<String, *>) {
        val elementList = map["children"]
        if (elementList != null) {
            elementList as List<Map<String, *>>
            elementList.forEach {
                processElement(it)
            }
        } else {
            logger.error("No children element.")
            return
        }
    }


    //TODO: Finn ut behovet for validering.
    private fun addRedigerbarMaltekst(map: Map<String, *>) {
        val elementList = map["children"]
        if (elementList != null) {
            elementList as List<Map<String, *>>
            elementList.forEach {
                processElement(it)
            }
        } else {
            logger.error("No children element for redigerbar maltekst.")
            return
        }
    }

    private fun addElementWithPossiblyChildren(map: Map<String, *>) {
        val div = document.create.div {
            this.addElementWithPossiblyChildren(map = map)
        }
        val divElement = document.getElementById("div_content_id") as Node
        divElement.appendChild(div)
    }

    private fun Tag.addElementWithPossiblyChildren(map: Map<String, *>) {
        val elementType = map["type"]
        var children = emptyList<Map<String, *>>()

        val applyClasses =
            if ((map["textAlign"] == "text-align-right") || (map["align"] == "right")) {
                mutableSetOf("alignRight")
            } else if ((map["align"] == "left")) {
                mutableSetOf("alignLeft")
            } else mutableSetOf()

        val inlineStyles = mutableSetOf<String>()

        if (map.containsKey("indent")) {
            val indent = map["indent"] as Int
            val alignment = if (map["align"] == "right") "right" else "left"
            inlineStyles += "margin-$alignment: ${24 * indent}pt"
        }

        if (elementType == "placeholder") {
            if (placeholderTextMissingInChildren(map)) {
                if (validationMode) {
                    throw EmptyPlaceholderException("Placeholder error")
                } else {
                    val text = map["placeholder"]
                    addLeafElement(mapOf("text" to text), mutableSetOf("placeholder-text"))
                }
                return
            }
        }

        if (elementType != "page-break") {
            children = map["children"] as List<Map<String, *>>
        } else {
            applyClasses.add("pageBreak")
        }

        val element = when (elementType) {
            "standard-text", "placeholder" -> SPAN(initialAttributes = emptyMap(), consumer = this.consumer)
            "h1" -> H1(initialAttributes = emptyMap(), consumer = this.consumer)
            "h2" -> H2(initialAttributes = emptyMap(), consumer = this.consumer)
            "h3" -> H3(initialAttributes = emptyMap(), consumer = this.consumer)
            "h4" -> H4(initialAttributes = emptyMap(), consumer = this.consumer)
            "h5" -> H5(initialAttributes = emptyMap(), consumer = this.consumer)
            "h6" -> H6(initialAttributes = emptyMap(), consumer = this.consumer)
            "p" -> P(initialAttributes = emptyMap(), consumer = this.consumer)
            "ul" -> UL(initialAttributes = emptyMap(), consumer = this.consumer)
            "ol" -> OL(initialAttributes = emptyMap(), consumer = this.consumer)
            "li" -> LI(initialAttributes = emptyMap(), consumer = this.consumer)
            "table" -> {
//                val colSizesInPx = map["colSizes"] as List<Int>
//                inlineStyles += "width: ${(colSizesInPx.sumOf { it.coerceAtLeast(48) } * pxToPtRatio) + colSizesInPx.size}pt;"
                TABLE(initialAttributes = emptyMap(), consumer = this.consumer)
            }

            "tr" -> {
                if (map.containsKey("size")) {
                    val heightInPx = map["size"] as Int
                    inlineStyles += "height: ${(heightInPx * pxToPtRatio)}pt;"
                }
                TR(initialAttributes = emptyMap(), consumer = this.consumer)
            }

            "td" -> {
                if (map.containsKey("colSpan")) {
                    TD(initialAttributes = mapOf("colspan" to map["colSpan"].toString()), consumer = this.consumer)
                } else {
                    TD(initialAttributes = emptyMap(), consumer = this.consumer)
                }
            }

            "page-break", "indent", "lic" -> DIV(
                initialAttributes = emptyMap(),
                consumer = this.consumer
            )

            else -> {
                logger.warn("unknown element type: $elementType")
                return
            }
        }

        element.visit {
            classes = applyClasses
            style = inlineStyles.joinToString(";")

            //special handling for tables
            if (this is TABLE) {
                if (map.containsKey("colSizes")) {
                    val colSizesInPx = map["colSizes"] as List<Int>
                    colGroup {
                        colSizesInPx.forEach { colSizeInPx ->
                            val width = if (colSizeInPx == 0) {
                                "auto"
                            } else {
                                (colSizeInPx.coerceAtLeast(48) * pxToPtRatio).toString() + "pt"
                            }
                            col {
                                style = "width: ${width};"
                            }
                        }
                    }
                }

                //wrap in tbody
                tbody {
                    loopOverChildren(children)
                }
            } else {
                loopOverChildren(children)
            }
        }
    }

    private fun HTMLTag.loopOverChildren(
        children: List<Map<String, *>>,
    ) {
        children.forEach {
            when (it.getType()) {
                LEAF -> this.addLeafElement(it)
                ELEMENT -> this.addElementWithPossiblyChildren(map = it)
                else -> {}
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

    private fun Tag.addLeafElement(map: Map<String, *>, inputClasses: MutableSet<String> = mutableSetOf()) {
        var text = map["text"] ?: throw RuntimeException("no content here")
        text as String
        if (text.isEmpty()) {
            text = "\uFEFF"
        }

        if (map["bold"] == true) {
            inputClasses += "bold"
        }
        if (map["underline"] == true) {
            inputClasses += "underline"
        }
        if (map["italic"] == true) {
            inputClasses += "italic"
        }

        this.consumer.span {
            classes = inputClasses
            +text
        }
    }

    private fun addCurrentDate() {
        val formatter = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.forLanguageTag("no"))
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
            processElement(it)
        }

        //defaults for now
        if (!headerAndFooterExists(dataList)) {
            val span = document.getElementById("header_text")
            span.textContent = "Returadresse,\nNAV Klageinstans Midt-Norge, Postboks 2914 Torgarden, 7438 Trondheim"

            footer =
                "Postadresse: NAV Klageinstans Midt-Norge // Postboks 2914 Torgarden // 7438 Trondheim\\ATelefon: 21 07 17 30\\Anav.no"
        }

        //add css when we have a footer set
        val head = document.create.head {
            style {
                unsafe {
                    raw(
                        getCss(footer)
                    )
                }
            }
        }

        document.childNodes.item(0).insertBefore(head, document.childNodes.item(0).firstChild)

        secureLogger.debug(document.serialize())
        return document
    }

    private fun headerAndFooterExists(list: List<Map<String, *>>) =
        list.any { it["type"] == "header" } && list.any { it["type"] == "footer" }

    private fun processElement(map: Map<String, *>) {
        when (map.getType()) {
            REGELVERK -> addRegelverk(map)
            REGELVERK_CONTAINER -> addRegelverkContainer(map)
            REDIGERBAR_MALTEKST -> addRedigerbarMaltekst(map)
            LABEL_CONTENT_ELEMENT -> addLabelContentElement(map)
            SIGNATURE_ELEMENT -> addSignatureElement(map)
            ELEMENT, INDENT -> addElementWithPossiblyChildren(map)
            DOCUMENT_LIST -> addDocumentList(map)
            MALTEKST -> addMaltekst(map)
            CURRENT_DATE -> addCurrentDate()
            HEADER -> addHeader(map)
            FOOTER -> setFooter(map)
            LEAF -> {}
            IGNORED -> {}
        }
    }

    private fun placeholderTextMissingInChildren(map: Map<String, *>): Boolean {
        val children = map["children"] as List<Map<String, *>>
        return children.any { it["text"] == null || it["text"].toString().trim('​').trim().isEmpty() }
    }

    private fun addHeader(map: Map<String, *>) {
        val span = document.getElementById("header_text")
        span.textContent = map["content"]?.toString() ?: " "
    }

    private fun setFooter(map: Map<String, *>) {
        footer = map["content"]?.toString()?.replace("\n", "\\A") ?: ""
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
                "header" -> HEADER
                "footer" -> FOOTER
                "redigerbar-maltekst" -> REDIGERBAR_MALTEKST
                "regelverk" -> REGELVERK
                "regelverk-container" -> REGELVERK_CONTAINER
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
    REGELVERK,
    REGELVERK_CONTAINER,
    REDIGERBAR_MALTEKST,
    CURRENT_DATE,
    HEADER,
    FOOTER,
    INDENT,
    IGNORED,
}

const val pxToPtRatio = 0.75