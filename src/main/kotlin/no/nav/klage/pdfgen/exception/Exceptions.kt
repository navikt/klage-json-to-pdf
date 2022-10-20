package no.nav.klage.pdfgen.exception

class ValidationException(msg: String) : RuntimeException(msg)

enum class ValidationErrorTypes {
    PLACEHOLDER
}