package no.nav.klage.pdfgen

import no.nav.klage.pdfgen.api.view.InnholdsfortegnelseRequest
import no.nav.klage.pdfgen.service.InnholdsfortegnelseService
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Path
import java.time.LocalDate


class GenerateInnholdsfortegnelsePDFTest {

    val path = "src/test/resources/"

    @Test
    fun `generate pdf from full input`() {
        val data = InnholdsfortegnelseService().getInnholdsfortegnelsePDFAsByteArray(InnholdsfortegnelseRequest(
            dokumenterUnderArbeid = listOf(
                InnholdsfortegnelseRequest.Document(
                    tittel = "Vedtak 2024",
                    tema = "Foreldrepenger",
                    dato = LocalDate.now(),
                    avsenderMottaker = "Kalle Anka, Kajsa Anka",
                    saksnummer = "123456",
                    type = "U"
                ),
                InnholdsfortegnelseRequest.Document(
                    tittel = "ROL notat 2023",
                    tema = "Foreldrepenger",
                    dato = LocalDate.now(),
                    avsenderMottaker = "Kalle Anka, Kajsa Anka",
                    saksnummer = "123456",
                    type = "U"
                )
            ),
            journalfoerteDokumenter = listOf(
                InnholdsfortegnelseRequest.Document(
                    tittel = "Vedtak 2023",
                    tema = "Foreldrepenger",
                    dato = LocalDate.now().minusMonths(4),
                    avsenderMottaker = "Kalle Anka, Kajsa Anka",
                    saksnummer = "123456",
                    type = "U"
                ),
                InnholdsfortegnelseRequest.Document(
                    tittel = "Klage 2023",
                    tema = "Foreldrepenger",
                    dato = LocalDate.now().minusMonths(5),
                    avsenderMottaker = "Knatte Anka",
                    saksnummer = "123456",
                    type = "I"
                )
            )
        ))
        Files.write(Path.of("test.pdf"), data)
    }

}
