package no.nav.klage.pdfgen

import no.nav.klage.pdfgen.service.PDFGenService
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Path


class GeneratePDF {

    @Test
    fun `generate pdf`() {
        val data = PDFGenService().getPDFAsByteArray(json)
        Files.write(Path.of("test.pdf"), data)
    }

}

val json = """
[
  {
    "type": "paragraph",
    "children": [
      {
        "text": "Ikke innrykket avsnitt Ikke innrykket avsnitt Ikke innrykket avsnitt Ikke innrykket avsnitt Ikke innrykket avsnitt Ikke innrykket avsnitt Ikke innrykket avsnitt Ikke innrykket avsnitt Ikke innrykket avsnitt Ikke innrykket avsnitt  "
      }
    ]
  },
  {
    "type": "indent",
    "children": [
      {
        "type": "paragraph",
        "children": [
          {
            "text": "Litt innrykket avsnitt Litt innrykket avsnitt Litt innrykket avsnitt Litt innrykket avsnitt Litt innrykket avsnitt Litt innrykket avsnitt Litt innrykket avsnitt Litt innrykket avsnitt Litt innrykket avsnitt Litt innrykket avsnitt Litt innrykket avsnitt (1x)"
          }
        ]
      },
      {
        "type": "indent",
        "children": [
          {
            "type": "paragraph",
            "children": [
              {
                "text": "Litt mer innrykket avsnitt Litt mer innrykket avsnitt Litt mer innrykket avsnitt Litt mer innrykket avsnitt Litt mer innrykket avsnitt Litt mer innrykket avsnitt Litt mer innrykket avsnitt Litt mer innrykket avsnitt (2x)"
              }
            ]
          },
          {
            "type": "indent",
            "children": [
              {
                "type": "paragraph",
                "children": [
                  {
                    "text": "Veldig innrykket avsnitt Veldig innrykket avsnitt Veldig innrykket avsnitt Veldig innrykket avsnitt Veldig innrykket avsnitt Veldig innrykket avsnitt Veldig innrykket avsnitt Veldig innrykket avsnitt Veldig innrykket avsnitt Veldig innrykket avsnitt  (3x)"
                  }
                ]
              }
            ]
          }
        ]
      }
    ]
  }
]
""".trimIndent()
