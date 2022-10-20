package no.nav.klage.pdfgen.config

import no.nav.klage.pdfgen.exception.ValidationException
import no.nav.klage.pdfgen.util.getLogger
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.NativeWebRequest
import org.zalando.problem.Problem
import org.zalando.problem.Status
import org.zalando.problem.spring.web.advice.AdviceTrait
import org.zalando.problem.spring.web.advice.ProblemHandling

@ControllerAdvice
class ProblemHandlingControllerAdvice : OurOwnExceptionAdviceTrait, ProblemHandling

interface OurOwnExceptionAdviceTrait : AdviceTrait {

    companion object {
        @Suppress("JAVA_CLASS_ON_COMPANION")
        private val logger = getLogger(javaClass.enclosingClass)
    }

    @ExceptionHandler
    fun handleValidationException(
        ex: ValidationException,
        request: NativeWebRequest
    ): ResponseEntity<Problem> =
        create(Status.BAD_REQUEST, ex, request)
}
