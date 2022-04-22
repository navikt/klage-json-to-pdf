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
    "maltekst": null,
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
    "threadIds": []
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
    "threadIds": []
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "Prosessfullmektig: "
      }
    ]
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "Saken gjelder: Klagen din av [] på NAV []s vedtak av "
      }
    ]
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "Problemstilling: Om"
      }
    ]
  },
  {
    "type": "heading-one",
    "children": [
      {
        "text": "Vedtak"
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
        "text": "Vi har avvist klagen din fordi du ikke har overholdt klagefristen."
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
        "text": "Vi har opphevet vedtaket og sendt saken tilbake til NAV [], som skal behandle den på nytt."
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
        "text": "Konklusjonen vår"
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
        "text": "Du har rett til å anke"
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
        "text": "\nDenne avgjørelsen er ikke et vedtak som gjelder individuelle rettigheter eller plikter (enkeltvedtak), men en prosessuell beslutning. Du kan ikke klage eller anke over denne beslutningen, jf. forvaltningsloven §§ 2 og 28 og lov om anke til trygderetten § 2.\n"
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
    "type": "heading-one",
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
        "text": "Informasjon om dekning av sakskostnader"
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
    "maltekst": null,
    "threadIds": []
  },
  {
    "type": "maltekst",
    "source": "regards",
    "children": [
      {
        "text": ""
      }
    ],
    "maltekst": null,
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
    "threadIds": []
  }
]
""".trimIndent()
