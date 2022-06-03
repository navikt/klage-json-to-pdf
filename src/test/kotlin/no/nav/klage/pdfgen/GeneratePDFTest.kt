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
    "type": "header",
    "children": [
      {
        "text": ""
      }
    ],
    "threadIds": [],
    "content": "Test for topptekst/bunntekst"
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
    "result": "Klager: GOD SEKK"
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
    "result": "Fødselsnummer: 294371 17843"
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
        "text": ": Klagen din av\t\t på NAV \t\tsitt vedtak av\t\t. "
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
    "content": [
      {
        "id": "5cf29eb0-02ac-4c03-b64c-b5a0a8f097ce",
        "title": "Tittel - Vedtak",
        "textType": "MALTEKST",
        "content": [
          {
            "type": "heading-two",
            "children": [
              {
                "text": "Vedtak"
              }
            ]
          }
        ],
        "plainText": null,
        "version": 1,
        "hjemler": [],
        "ytelser": [],
        "utfall": [
          "4",
          "5",
          "6",
          "7",
          "8"
        ],
        "enheter": [],
        "sections": [
          "section-mår"
        ],
        "templates": [],
        "created": "2022-05-23T11:12:23.114336",
        "modified": "2022-06-01T16:46:42.693599"
      }
    ],
    "threadIds": []
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "Vi har [delvis] omgjort vedtaket, slik at ..."
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
        "text": "Vi har kommet fram til at \t\t. Du har [ikke] fått medhold i klagen din."
      }
    ]
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "Vedtaket er gjort etter \t\t § \t.\t"
      }
    ]
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "Vi kan ikke vurdere om du har rett til \t\t, fordi \t\t. NAV skal behandle saken på nytt."
      }
    ]
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "Saken er vurdert etter \t\t §\t."
      }
    ]
  },
  {
    "type": "maltekst",
    "section": "section-ape",
    "children": [
      {
        "text": ""
      }
    ],
    "content": [
      {
        "id": "61c1ace0-f0cb-4ed3-aa2f-92eef555fc7b",
        "title": "Du har rett til å anke",
        "textType": "MALTEKST",
        "content": [
          {
            "type": "heading-three",
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
                "text": "]. "
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
          }
        ],
        "plainText": null,
        "version": 1,
        "hjemler": [],
        "ytelser": [],
        "utfall": [
          "4",
          "5",
          "6",
          "7",
          "8"
        ],
        "enheter": [],
        "sections": [
          "section-ape"
        ],
        "templates": [],
        "created": "2022-05-19T10:11:17.732855",
        "modified": "2022-06-03T10:38:12.88089"
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
    "section": "section-gris",
    "children": [
      {
        "text": ""
      }
    ],
    "content": [
      {
        "id": "3fd23fd3-82fc-4521-a51c-6d6aefcc1f35",
        "title": "Sakskostnader",
        "textType": "MALTEKST",
        "content": [
          {
            "type": "heading-two",
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
          }
        ],
        "plainText": null,
        "version": 1,
        "hjemler": [],
        "ytelser": [],
        "utfall": [
          "4",
          "5"
        ],
        "enheter": [],
        "sections": [
          "section-gris"
        ],
        "templates": [],
        "created": "2022-05-19T14:11:46.193767",
        "modified": "2022-06-01T16:46:42.693354"
      }
    ],
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
      "name": "F_Z994862 E_Z994862",
      "title": "Seniorrådgiver/saksbehandler"
    },
    "medunderskriver": {
      "name": "F_Z994863 E_Z994863",
      "title": "TITTEL MANGLER"
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
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "Folketrygdloven § 14-10 sjuende ledd (før 1. oktober 2021)",
        "underline": true
      }
    ]
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "\"",
        "underline": false
      },
      {
        "text": "Foreldrepenger må tas ut sammenhengende fra det tidspunkt uttaket starter, se likevel § 14-11.\""
      }
    ]
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "Folketrygdloven § 14-11 (før 1. oktober 2021)",
        "underline": true
      }
    ]
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "\"Uttak av foreldrepenger kan utsettes"
      }
    ]
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "a)\tnår den som mottar foreldrepenger har lovbestemt ferie,​1"
      }
    ]
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "b)\tnår den som mottar foreldrepenger er i inntektsgivende arbeid på heltid,"
      }
    ]
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "c)\tdersom stønadsmottakeren på grunn av sykdom eller skade er helt avhengig av hjelp til å ta seg av barnet eller er innlagt i helseinstitusjon,"
      }
    ]
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "d)\tdersom barnet er innlagt i helseinstitusjon."
      }
    ]
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "Ved forhold som nevnt i bokstav b, må det foreligge skriftlig avtale med arbeidsgiver. For selvstendig næringsdrivende og frilansere må det foreligge skriftlig avtale med Arbeids- og velferdsetaten."
      }
    ]
  },
  {
    "type": "paragraph",
    "children": [
      {
        "text": "Forhold som nevnt i bokstav c må dokumenteres med legeerklæring.\""
      }
    ],
    "textAlign": "text-align-left"
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "Folketrygdloven § 14-6",
        "underline": true
      }
    ]
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "\"Rett til foreldrepenger opptjenes gjennom yrkesaktivitet. Både moren og faren kan opptjene rett til foreldrepenger ved å være yrkesaktiv med pensjonsgivende inntekt (§ 3-15) i minst seks av de siste ti månedene før vedkommendes uttak av foreldrepenger tar til, se §§ 14-10 første og annet ledd og 14-14 annet ledd. Ved adopsjon må moren ha opptjent rett til foreldrepenger ved omsorgsovertakelsen."
      }
    ]
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "Likestilt med yrkesaktivitet er tidsrom da det er gitt en ytelse til livsopphold i form av dagpenger under arbeidsløshet etter kapittel 4, sykepenger etter kapittel 8, stønad ved barns sykdom m.m. etter kapittel 9 eller arbeidsavklaringspenger etter kapittel 11, eller enten foreldrepenger eller svangerskapspenger etter kapitlet her."
      }
    ]
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "Likestilt med yrkesaktivitet er også tidsrom med"
      }
    ]
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "a.\tlønn fra arbeidsgiver under permisjon i forbindelse med videre- og etterutdanning,"
      }
    ]
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "b.\tventelønn etter lov av 4. mars 1983 nr. 3 om statens tjenestemenn m.m.​1 § 13 nr. 6,"
      }
    ]
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "c.\tvartpenger etter lov av 28. juli 1949 nr. 26 om Statens pensjonskasse § 24 tredje ledd,"
      }
    ]
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "d.\tetterlønn fra arbeidsgiver,"
      }
    ]
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "e.\tavtjening av militærtjeneste eller obligatorisk sivilforsvarstjeneste,​2"
      }
    ]
  },
  {
    "type": "paragraph",
    "textAlign": "text-align-left",
    "children": [
      {
        "text": "f.\tytelse i medhold av midlertidig lov om kompensasjonsytelse for selvstendig næringsdrivende og frilansere som har mistet inntekt som følge av utbrudd av covid-19.\""
      }
    ]
  },
  {
    "type": "footer",
    "children": [
      {
        "text": ""
      }
    ],
    "threadIds": [],
    "content": "Test for topptekst/bunntekst"
  }
]
""".trimIndent()
