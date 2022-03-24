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
        "source": "sakenGjelder.name",
        "content": "KRAFTIG KAKKERLAKK"
      },
      {
        "type": "label-content",
        "id": "fnr",
        "label": "Fødselsnummer",
        "source": "sakenGjelder.fnr",
        "content": "024467 01749"
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
        "source": "utfall-title",
        "content": "Vedtak"
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
                "text": "[Alt 3 avvisning] Vi har avvist klagen din "
              },
              {
                "text": "fordi",
                "bold": false
              },
              {
                "text": " du ikke har overholdt klagefristen."
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
                "text": "dfsdfsdfsdfs"
              }
            ]
          },
          {
            "type": "paragraph",
            "textAlign": "text-align-left",
            "children": [
              {
                "text": "sdfsdfsdf"
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
        "content": [
          "Vedtak-/utfallsbrev",
          "dummy-01.pdf",
          "Enkelt utfall-/vedtaksbrev",
          "dummy-01.pdf",
          "MASKERT_FELT"
        ]
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
        "source": "questions",
        "content": [
          {
            "type": "heading-one",
            "children": [
              {
                "text": "Har du spørsmål?"
              }
            ]
          },
          {
            "type": "paragraph",
            "textAlign": "text-align-left",
            "children": [
              {
                "text": "Du finner mer informasjon på nav.no. Hvis du ikke finner svar på spørsmålet ditt, kontakt oss på nav.no/kontakt."
              }
            ]
          }
        ]
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
        "source": "regards",
        "content": [
          {
            "type": "paragraph",
            "textAlign": "text-align-left",
            "children": [
              {
                "text": "Med vennlig hilsen\nNAV Klageinstans"
              }
            ]
          }
        ]
      }
    ]
  },
  {
    "type": "signature",
    "id": "signature",
    "content": {
      "useShortName": true,
      "medunderskriver": {
        "name": "K. Nordmann",
        "title": "Avdelingsdirektør"
      },
      "saksbehandler": {
        "name": "C. Skrøvseth",
        "title": "Seniorrådgiver"
      }
    }
  }
]
""".trimIndent()
