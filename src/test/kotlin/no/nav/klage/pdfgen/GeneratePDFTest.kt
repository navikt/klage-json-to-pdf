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
    "type": "current-date",
    "children": [
      {
        "text": ""
      }
    ]
  },
  {
    "type": "maltekst",
    "section": "section-address",
    "children": [
      {
        "text": ""
      }
    ],
    "content": [
      {
        "id": "1db1f736-c6e9-4ecc-9e63-7dd5058fdb25",
        "title": "Adressetest",
        "textType": "MALTEKST",
        "content": [
          {
            "type": "paragraph",
            "textAlign": "text-align-left",
            "children": [
              {
                "text": "NAV Klageinstans Nord/sør/øst/vest\nPostboks 4321\n0000 Norge",
                "bold": false,
                "italic": false,
                "underline": false
              }
            ]
          }
        ],
        "plainText": null,
        "version": 1,
        "hjemler": [],
        "ytelser": [],
        "utfall": [],
        "enheter": [],
        "sections": [
          "section-address"
        ],
        "templates": [
          "klagevedtak"
        ],
        "created": "2022-06-02T17:16:48.986144",
        "modified": "2022-06-02T17:20:46.767572"
      }
    ],
    "threadIds": []
  },
  {
    "type": "maltekst",
    "section": "section-esel",
    "children": [
      {
        "text": ""
      }
    ],
    "content": [
      {
        "id": "4c5e271c-6f11-456e-9e01-ae72981b995e",
        "title": "Dokumenttittel ",
        "textType": "MALTEKST",
        "content": [
          {
            "type": "heading-one",
            "children": [
              {
                "text": "NAV Klageinstans har behandlet klagen din"
              }
            ]
          }
        ],
        "plainText": null,
        "version": 1,
        "hjemler": [],
        "ytelser": [],
        "utfall": [],
        "enheter": [],
        "sections": [
          "section-esel"
        ],
        "templates": [
          "klagevedtak-legacy",
          "klagevedtak"
        ],
        "created": "2022-05-19T14:26:35.125398",
        "modified": "2022-06-01T16:46:42.692943"
      }
    ],
    "threadIds": []
  },
  {
    "type": "label-content",
    "children": [
      {
        "text": ""
      }
    ],
    "source": "sakenGjelder.name",
    "label": "Klager",
    "threadIds": [],
    "result": "Klager: KREATIV STAFFELI"
  },
  {
    "type": "label-content",
    "children": [
      {
        "text": ""
      }
    ],
    "source": "sakenGjelder.fnr",
    "label": "Fødselsnummer",
    "threadIds": [],
    "result": "Fødselsnummer: 284169 04490"
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "Prosessfullmektig",
        "bold": true
      },
      {
        "text": ":"
      }
    ]
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "Saken gjelder",
        "bold": true
      },
      {
        "text": ": Klagen din av på NAV sitt vedtak av. "
      }
    ]
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "Problemstilling",
        "bold": true
      },
      {
        "text": ": Om "
      }
    ]
  },
  {
    "type": "maltekst",
    "section": "section-mår",
    "children": [
      {
        "text": ""
      }
    ],
    "content": [],
    "threadIds": []
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "Vi har opphevet vedtaket og sendt saken tilbake til NAV ... , som skal behandle den på nytt."
      }
    ]
  },
  {
    "type": "heading-two",
    "children": [
      {
        "text": "I klagen din har du lagt vekt på:"
      }
    ]
  },
  {
    "type": "bullet-list",
    "children": [
      {
        "type": "list-item",
        "children": [
          {
            "type": "list-item-container",
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
    "type": "heading-two",
    "children": [
      {
        "text": "I vurderingen vår har vi lagt vekt på disse opplysningene:"
      }
    ]
  },
  {
    "type": "bullet-list",
    "children": [
      {
        "type": "list-item",
        "children": [
          {
            "type": "list-item-container",
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
    "type": "maltekst",
    "section": "section-mus",
    "children": [
      {
        "text": ""
      }
    ],
    "content": [
      {
        "id": "dc7988ec-fc0c-45c1-a1b2-7c9c85b36569",
        "title": "Tittel - Vurderingen vår",
        "textType": "MALTEKST",
        "content": [
          {
            "type": "heading-two",
            "children": [
              {
                "text": "Vurderingen vår"
              }
            ]
          }
        ],
        "plainText": null,
        "version": 1,
        "hjemler": [],
        "ytelser": [],
        "utfall": [],
        "enheter": [],
        "sections": [
          "section-mus"
        ],
        "templates": [],
        "created": "2022-05-19T11:12:55.31585",
        "modified": "2022-06-01T16:46:42.692902"
      },
      {
        "id": "4245b46f-9312-4244-b05b-18cfdd95b29b",
        "title": "Vurderingen vår for omsorgspenger",
        "textType": "MALTEKST",
        "content": [
          {
            "type": "heading-two",
            "children": [
              {
                "text": "Vurderingen vår for omsorgspenger"
              }
            ]
          }
        ],
        "plainText": "",
        "version": 1,
        "hjemler": [],
        "ytelser": [
          "1"
        ],
        "utfall": [],
        "enheter": [],
        "sections": [
          "section-mus"
        ],
        "templates": [
          "klagevedtak"
        ],
        "created": "2022-06-08T10:18:24.485373",
        "modified": "2022-06-08T10:19:13.127728"
      }
    ],
    "threadIds": []
  },
  {
    "type": "paragraph",
    "children": [
      {
        "text": ""
      }
    ],
    "textAlign": "text-align-left"
  },
  {
    "type": "maltekst",
    "section": "section-elg",
    "children": [
      {
        "text": ""
      }
    ],
    "content": [
      {
        "id": "8b8cc611-7a62-4186-9755-70c55711bafb",
        "title": "Tittel - Konklusjonen vår",
        "textType": "MALTEKST",
        "content": [
          {
            "type": "heading-two",
            "children": [
              {
                "text": "Konklusjonen vår"
              }
            ]
          }
        ],
        "plainText": null,
        "version": 1,
        "hjemler": [],
        "ytelser": [],
        "utfall": [],
        "enheter": [],
        "sections": [
          "section-elg"
        ],
        "templates": [],
        "created": "2022-05-19T11:12:40.790939",
        "modified": "2022-06-01T16:46:42.692624"
      }
    ],
    "threadIds": []
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "Vi har kommet fram til at ... Du har [ikke] fått medhold i klagen din."
      }
    ]
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "Vedtaket er gjort etter ... § ...\t"
      }
    ]
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "Vi kan ikke vurdere om du har rett til ..., fordi ... . NAV skal behandle saken på nytt."
      }
    ]
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "Saken er vurdert etter ... § ..."
      }
    ]
  },
  {
    "type": "maltekst",
    "section": "section-gris",
    "children": [
      {
        "text": ""
      }
    ],
    "content": [],
    "threadIds": []
  },
  {
    "type": "maltekst",
    "section": "section-ape",
    "children": [
      {
        "text": ""
      }
    ],
    "content": [],
    "threadIds": []
  },
  {
    "type": "maltekst",
    "section": "section-sel",
    "children": [
      {
        "text": ""
      }
    ],
    "content": [
      {
        "id": "cf6cf64b-d464-41fa-ac3a-3112a1625092",
        "title": "Generell informasjon",
        "textType": "MALTEKST",
        "content": [
          {
            "type": "heading-two",
            "children": [
              {
                "text": "Du har rett til innsyn"
              }
            ]
          },
          {
            "type": "paragraph",
            "textAlign": "text-align-left",
            "children": [
              {
                "text": "Du har rett til å se dokumentene i saken din."
              }
            ]
          },
          {
            "type": "heading-two",
            "children": [
              {
                "text": "Informasjon om fri rettshjelp"
              }
            ]
          },
          {
            "type": "paragraph",
            "textAlign": "text-align-left",
            "children": [
              {
                "text": "Dette får du vite mer om hos Statsforvalteren eller advokat."
              }
            ]
          },
          {
            "type": "heading-two",
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
        ],
        "plainText": null,
        "version": 1,
        "hjemler": [],
        "ytelser": [],
        "utfall": [],
        "enheter": [],
        "sections": [
          "section-sel"
        ],
        "templates": [],
        "created": "2022-05-19T11:06:13.118438",
        "modified": "2022-06-02T09:17:09.426083"
      }
    ],
    "threadIds": []
  },
  {
    "type": "signature",
    "useShortName": false,
    "children": [
      {
        "text": ""
      }
    ],
    "threadIds": [],
    "saksbehandler": {
      "name": "Kari Nordmann",
      "title": "Fagleder/saksbehandler"
    }
  },
  {
    "type": "page-break",
    "children": [
      {
        "text": ""
      }
    ]
  },
  {
    "type": "maltekst",
    "section": "section-gnu",
    "children": [
      {
        "text": ""
      }
    ],
    "content": [
      {
        "id": "75ef2a36-63d3-439d-8dd5-9f28c3714afa",
        "title": "Tittel - Regelverket som gjelder i saken ",
        "textType": "MALTEKST",
        "content": [
          {
            "type": "heading-two",
            "children": [
              {
                "text": "Regelverket som gjelder i saken"
              }
            ]
          }
        ],
        "plainText": null,
        "version": 1,
        "hjemler": [],
        "ytelser": [],
        "utfall": [],
        "enheter": [],
        "sections": [
          "section-gnu"
        ],
        "templates": [],
        "created": "2022-05-19T11:22:23.85064",
        "modified": "2022-06-01T16:46:42.693257"
      }
    ],
    "threadIds": []
  },
  {
    "type": "paragraph",
    "children": [
      {
        "text": ""
      }
    ],
    "textAlign": "text-align-left"
  }
]
""".trimIndent()
