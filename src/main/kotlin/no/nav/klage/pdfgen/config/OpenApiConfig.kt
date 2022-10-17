package no.nav.klage.pdfgen.config

import no.nav.klage.pdfgen.api.PDFGenController
import org.springdoc.core.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun apiInternal(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("standard")
            .packagesToScan(PDFGenController::class.java.packageName)
            .pathsToMatch("/**")
            .build()
    }
}
