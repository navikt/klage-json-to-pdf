package no.nav.klage.pdfgen.api

import no.nav.klage.pdfgen.service.PDFGenService
import no.nav.klage.pdfgen.util.getLogger
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
class PDFGenController(
    private val pdfGenService: PDFGenService
) {

    companion object {
        @Suppress("JAVA_CLASS_ON_COMPANION")
        private val logger = getLogger(javaClass.enclosingClass)
    }

    @ResponseBody
    @PostMapping("/topdf")
    fun toPDF(
        @RequestBody json: String
    ): ResponseEntity<ByteArray> {
        logger.debug("toPDF(). received json: {}", json)

        val (filename, data) = pdfGenService.getPDFAsByteArray(json)

        val responseHeaders = HttpHeaders()
        responseHeaders.contentType = MediaType.APPLICATION_PDF
        responseHeaders.add("Content-Disposition", "inline; filename=$filename.pdf")
        return ResponseEntity(
            data,
            responseHeaders,
            HttpStatus.OK
        )
    }

}