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
    "type": "maltekst",
    "children": [
      {
        "text": ""
      }
    ],
    "source": "document-title",
    "maltekst": [
      {
        "type": "heading-one",
        "children": [
          {
            "text": "NAV Klageinstans har behandlet klagen din"
          }
        ]
      }
    ],
    "threadIds": [
    ]
  },
  {
    "type": "label-content",
    "children": [
      {
        "text": ""
      }
    ],
    "result": "Klager: KRAFTIG KAKKERLAKK",
    "source": "sakenGjelder.name",
    "label": "Klager",
    "threadIds": [
    ]
  },
  {
    "type": "label-content",
    "children": [
      {
        "text": ""
      }
    ],
    "result": "Fødselsnummer: 024467 01749",
    "source": "sakenGjelder.fnr",
    "label": "Fødselsnummer",
    "threadIds": [
    ]
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "Prosessfullmektig",
        "commentThreadId_0dde9c17-c630-4b6c-a56d-d8b4ba6630b6": true
      },
      {
        "text": ": "
      }
    ]
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "Saken gjelder: "
      },
      {
        "text": "Klagen",
        "commentThreadId_e717c817-1fd7-4ea1-81b0-eec932a55ac3": true,
        "commentThreadId_f7d4bd55-3869-44b7-b662-0d0c455d07a4": true
      },
      {
        "text": " din av [] på NAV []s vedtak av "
      }
    ]
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "Problemstilling: Om "
      }
    ]
  },
  {
    "type": "heading-one",
    "children": [
      {
        "text": "Vedtak",
        "commentThreadId_afe25029-1789-4588-b332-9ada99b89b59": true
      }
    ]
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "[Alt 1 stadfestelse]\n",
        "bold": true
      },
      {
        "text": "Vi er enige i vedtaket."
      }
    ]
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "[Alt 2 omgjøring]\n",
        "bold": true
      },
      {
        "text": "Vi har omgjort vedtaket, slik at"
      }
    ]
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "[Alt 3 avvisning]\n",
        "bold": true
      },
      {
        "text": "Vi har avvist klagen",
        "commentThreadId_93014183-baca-4923-9f17-44f4cb26bdb0": true
      },
      {
        "text": " din fordi du ikke har overholdt klagefristen."
      }
    ]
  },
  {
    "type": "heading-one",
    "children": [
      {
        "text": "Beslutning"
      }
    ]
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "Vi har opphevet vedtaket og sendt saken tilbake til NAV [], "
      },
      {
        "text": "som skal behandle den",
        "commentThreadId_61733251-9045-4d17-8982-440ea265fcaf": true
      },
      {
        "text": " på nytt."
      }
    ]
  },
  {
    "type": "heading-one",
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
                "text": "osv."
              }
            ]
          }
        ]
      }
    ]
  },
  {
    "type": "heading-one",
    "children": [
      {
        "text": "I vurderingen vår har vi lagt vekt på disse dokumentene:"
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
                "text": "osv."
              }
            ]
          }
        ]
      }
    ]
  },
  {
    "type": "heading-one",
    "children": [
      {
        "text": "Vurderingen vår"
      }
    ]
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "For å ha rett til ["
      },
      {
        "text": "stønad",
        "italic": true
      },
      {
        "text": "], må du"
      }
    ]
  },
  {
    "type": "heading-one",
    "children": [
      {
        "text": "Konklusjonen vår",
        "commentThreadId_abe11116-8ee5-4416-8f52-59d23df693ef": true
      }
    ]
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "[Vedtak]",
        "bold": true
      },
      {
        "text": "Vi har kommet fram til at du ["
      },
      {
        "text": "ikke",
        "italic": true
      },
      {
        "text": "] har rett til [], og gir deg derfor ["
      },
      {
        "text": "ikke medhold><medhold",
        "italic": true
      },
      {
        "text": "] i klagen din."
      }
    ]
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "Vedtaket er gjort etter folketrygdloven § ["
      },
      {
        "text": "hjemmel",
        "italic": true
      },
      {
        "text": "]."
      }
    ]
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "[Beslutning]",
        "bold": true
      },
      {
        "text": " Vi kan ikke vurdere om du har rett til ["
      },
      {
        "text": "stønad",
        "italic": true
      },
      {
        "text": "], fordi . NAV ["
      },
      {
        "text": "enhet",
        "italic": true
      },
      {
        "text": "] skal behandle saken på nytt."
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
  },
  {
    "type": "heading-one",
    "children": [
      {
        "text": "Du har rett til å anke",
        "commentThreadId_1b147e24-36f8-42c1-9ab2-56653c91ca8c": true
      }
    ]
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "Hvis du mener dette vedtaket er feil, kan du anke til Trygderetten innen seks uker fra den datoen vedtaket kom fram til deg. Du finner informasjon, skjema og første side for innsending på "
      },
      {
        "text": "www.nav.no/klage",
        "underline": true
      },
      {
        "text": ". Velg NAV Klageinstans ["
      },
      {
        "text": "avdeling",
        "italic": true
      },
      {
        "text": "]."
      }
    ]
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "Anken må være underskrevet av deg."
      }
    ]
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "I trygdesaker må du først anke til Trygderetten før du kan ta saken videre til lagmannsretten."
      }
    ]
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "[",
        "italic": true
      },
      {
        "text": "Husk",
        "bold": true,
        "italic": true
      },
      {
        "italic": true,
        "text": ": Ta ut avsnittet om anke ved beslutning om oppheving og erstatt med]"
      },
      {
        "text": "\n"
      },
      {
        "text": "Denne avgjørelsen er ikke et vedtak",
        "commentThreadId_ffd54395-195c-4090-ba91-144905e1ea79": true
      },
      {
        "text": " som gjelder individuelle rettigheter eller plikter (enkeltvedtak), men en prosessuell beslutning. Du kan ikke klage eller anke over denne beslutningen, jf. forvaltningsloven §§ 2 og 28 og lov om anke til trygderetten § 2.\n"
      },
      {
        "text": "[",
        "italic": true
      },
      {
        "text": "Husk",
        "bold": true,
        "italic": true
      },
      {
        "text": ": dersom avgjørelsen delvis er et enkeltvedtak (stadfestelse/ omgjøring) og delvis en beslutning (opphevelse), kan parten påanke den delen av avgjørelsen som er et enkeltvedtak. Avsnittet om ankeadgang må da stå.]",
        "italic": true
      }
    ]
  },
  {
    "type": "heading-one",
    "children": [
      {
        "text": "Du har rett til innsyn",
        "commentThreadId_30a725f1-d9de-4c75-9d4a-893cbaff589a": true
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
    "type": "heading-one",
    "children": [
      {
        "text": "Informasjon om fri rettshjelp",
        "commentThreadId_812d9bdb-eb66-4786-a12d-65f80325b17c": true
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
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "[Dette avsnittet skal bare være med ved omgjøring eller delvis omgjøring]",
        "bold": true
      }
    ]
  },
  {
    "type": "heading-one",
    "children": [
      {
        "text": "Informasjon om dekning av sakskostnader",
        "commentThreadId_2756ba4f-2e73-407d-9d08-7402600b8520": true
      }
    ]
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "Dersom vi har gjort om vedtaket til din fordel kan du ha rett til å få dekket vesentlige kostnader som har vært nødvendige for å få endret vedtaket."
      }
    ]
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "Dette må du søke om innen tre uker fra den datoen dette vedtaket er kommet fram til deg. Når du søker om å få dekket utgifter til juridisk bistand, må du legge ved spesifisert timeliste og faktura. Søknaden sender du til NAV Klageinstans Sakskostnader, Postboks 644, Lundsiden, 4606 Kristiansand."
      }
    ]
  },
  {
    "type": "maltekst",
    "source": "questions",
    "children": [
      {
        "text": ""
      }
    ],
    "maltekst": [
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
    ],
    "threadIds": [
    ]
  },
  {
    "type": "maltekst",
    "source": "regards",
    "children": [
      {
        "text": ""
      }
    ],
    "maltekst": [
      {
        "type": "paragraph",
        "textAlign": "text-align-left",
        "children": [
          {
            "text": "Med vennlig hilsen\nNAV Klageinstans"
          }
        ]
      }
    ],
    "threadIds": [
    ]
  },
  {
    "type": "signature",
    "useShortName": false,
    "children": [
      {
        "text": ""
      }
    ],
    "threadIds": [
    ]
  }
]
""".trimIndent()
