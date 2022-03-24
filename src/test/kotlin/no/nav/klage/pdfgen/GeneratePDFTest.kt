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
    "type": "document-title",
    "id": "document-title",
    "content": "NAV Klageinstans har behandlet klagen din"
  },
  {
    "type": "section",
    "id": "data-section",
    "content": [
      {
        "type": "label-content",
        "id": "klager",
        "label": "Klager",
        "source": "sakenGjelder.name"
      },
      {
        "type": "label-content",
        "id": "fnr",
        "label": "Fødselsnummer",
        "source": "sakenGjelder.fnr"
      }
    ]
  },
  {
    "type": "section",
    "id": "section-vedtak",
    "content": [
      {
        "type": "section-title",
        "id": "vedtak-title",
        "source": "utfall-title"
      },
      {
        "id": "vedtak",
        "type": "rich-text",
        "content": [
          {
            "type": "paragraph",
            "textAlign": "text-align-left",
            "children": [
              {
                "text": "[Alt 1 stadfestelse] Vi er enige i vedtaket."
              }
            ]
          },
          {
            "type": "paragraph",
            "textAlign": "text-align-left",
            "children": [
              {
                "text": "[Alt 2 omgjøring] Vi har omgjort vedtaket, slik at"
              }
            ]
          },
          {
            "type": "paragraph",
            "textAlign": "text-align-left",
            "children": [
              {
                "text": "[Alt 3 avvisning] Vi har avvist klagen din fordi du ikke har overholdt klagefristen."
              }
            ]
          },
          {
            "type": "paragraph",
            "textAlign": "text-align-left",
            "children": [
              {
                "text": "[Beslutning] Vi har opphevet vedtaket og sendt saken tilbake til NAV, som skal behandle den på nytt."
              }
            ]
          }
        ]
      }
    ]
  },
  {
    "type": "section",
    "id": "section-din-vekt",
    "content": [
      {
        "type": "section-title",
        "id": "din-vekt-title",
        "content": "I klagen din har du lagt vekt på"
      },
      {
        "id": "din-vekt",
        "type": "rich-text",
        "content": [
          {
            "type": "paragraph",
            "textAlign": "text-align-left",
            "children": [
              {
                "text": ""
              }
            ]
          }
        ]
      }
    ]
  },
  {
    "type": "section",
    "id": "section-documents",
    "content": [
      {
        "type": "section-title",
        "id": "documents-title",
        "content": "I vurderingen vår har vi lagt vekt på disse dokumentene"
      },
      {
        "type": "document-list",
        "id": "document-list",
        "content": []
      }
    ]
  },
  {
    "type": "section",
    "id": "section-vurdering",
    "content": [
      {
        "type": "section-title",
        "id": "vurdering-title",
        "content": "Vurderingen vår"
      },
      {
        "id": "vurdering",
        "type": "rich-text",
        "content": [
          {
            "type": "paragraph",
            "textAlign": "text-align-left",
            "children": [
              {
                "text": ""
              }
            ]
          }
        ]
      }
    ]
  },
  {
    "type": "section",
    "id": "section-konklusjon",
    "content": [
      {
        "type": "section-title",
        "id": "konklusjon-title",
        "content": "Konklusjonen vår"
      },
      {
        "id": "konklusjon",
        "type": "rich-text",
        "content": [
          {
            "type": "paragraph",
            "textAlign": "text-align-left",
            "children": [
              {
                "text": "[Vedtak]Vi har kommet fram til at du [ikke] har rett til, og gir deg derfor [ikke medhold><medhold] i klagen din."
              }
            ]
          },
          {
            "type": "paragraph",
            "textAlign": "text-align-left",
            "children": [
              {
                "text": "Vedtaket er gjort etter folketrygdloven § [hjemmel]."
              }
            ]
          },
          {
            "type": "paragraph",
            "textAlign": "text-align-left",
            "children": [
              {
                "text": "[Beslutning] Vi kan ikke vurdere om du har rett til [stønad], fordi . NAV [enhet] skal behandle saken på nytt."
              }
            ]
          },
          {
            "type": "paragraph",
            "textAlign": "text-align-left",
            "children": [
              {
                "text": "Saken er vurdert etter folketrygdloven §."
              }
            ]
          }
        ]
      }
    ]
  },
  {
    "type": "section",
    "id": "section-anke",
    "content": [
      {
        "type": "rich-text",
        "id": "anke-text",
        "content": [
          {
            "type": "heading-one",
            "children": [
              {
                "text": "Du har rett til å anke"
              }
            ]
          },
          {
            "type": "paragraph",
            "textAlign": "text-align-left",
            "children": [
              {
                "text": "Hvis du mener dette vedtaket er feil, kan du anke til Trygderetten innen seks uker fra den datoen vedtaket kom fram til deg. Du finner informasjon, skjema og første side for innsending på www.nav.no/klage. Velg NAV Klageinstans [avdeling]."
              }
            ]
          }
        ]
      }
    ]
  },
  {
    "type": "section",
    "id": "section-questions",
    "content": [
      {
        "type": "static",
        "id": "questions",
        "source": "questions"
      }
    ]
  },
  {
    "type": "section",
    "id": "section-regards",
    "content": [
      {
        "type": "static",
        "id": "regards",
        "source": "regards"
      }
    ]
  },
  {
    "type": "signature",
    "id": "signature",
    "content": {
      "useShortName": false
    }
  }
]
""".trimIndent()
