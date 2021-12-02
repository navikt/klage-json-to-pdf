package no.nav.klage.pdfgen

import no.nav.klage.pdfgen.service.PDFGenService
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Path


class GeneratePDF {

    @Test
    fun `generate pdf`() {
        val (filename, data) = PDFGenService().getPDFAsByteArray(json)
        println("filename: $filename")
        Files.write(Path.of("test.pdf"), data)
    }

}

val json = """
{
  "id": "string",
  "title": "Tittel for dokumentet",
  "content": [
    {
      "id": "klager",
      "type": "text",
      "label": "Klager",
      "content": "Navn Navnesen"
    },
    {
      "id": "fnr",
      "type": "text",
      "label": "Fødselsnummer",
      "content": "1234567890"
    },
    {
      "id": "saksnummer",
      "type": "text",
      "label": "Saksnummer",
      "content": "987654321"
    },
    {
      "id": "prosess",
      "type": "text",
      "label": "Prosessfullmektig"
    },
    {
      "id": "about",
      "type": "text",
      "label": "Saken gjelder",
      "placeholder": "Klagen din av (dato) på (enhet) sitt vedtak av (dato)."
    },
    {
      "id": "problem",
      "type": "rich-text",
      "label": "Problemstilling",
      "placeholder": "Spørsmålet er om du (tekst) fra (dato).",
      "content": [
        {
          "type": "paragraph",
          "children": [
            {
              "text": "en p her."
            }
          ]
        }
      ]
    },
    {
      "id": "vedtak-block",
      "title": "Vår vurdering",
      "content": [
        {
          "id": "vedtak",
          "type": "rich-text",
          "content": [
            {
              "type": "standard-text",
              "standardText": "Dette er en standardtekst.",
              "standardTextId": "id-123",
              "children": [
                {
                  "text": "Dette er en standardtekst på ny side."
                }
              ]
            },
            {
              "type": "heading-one",
              "children": [
                {
                  "text": "Tittel for vedtaket h1"
                }
              ]
            },
            {
              "type": "heading-two",
              "children": [
                {
                  "text": "Tittel for vedtaket h2"
                }
              ]
            },
            {
              "type": "blockquote",
              "children": [
                {
                  "text": "Block quote."
                }
              ]
            },
            {
              "type": "blockquote",
              "textAlign": "text-align-right",
              "children": [
                {
                  "text": "Block quote. Høyrestilt."
                }
              ]
            },
            {
              "type": "paragraph",
              "children": [
                {
                  "text": "En venstrestilt paragraf med "
                },
                {
                  "text": "bold tekst",
                  "bold": true
                },
                {
                  "text": " "
                },
                {
                  "text": "kursiv tekst",
                  "italic": true
                },
                {
                  "text": " "
                },
                {
                  "text": "understreking",
                  "underline": true
                },
                {
                  "text": " og ingen formatering."
                }
              ]
            },
            {
              "type": "paragraph",
              "textAlign": "text-align-right",
              "children": [
                {
                  "text": "En høyrestilt paragraf."
                }
              ]
            },
            {
              "type": "paragraph",
              "textAlign": "text-align-left",
              "children": [
                {
                  "text": "En venstrestilt paragraf. Som er default, dersom 'textAlign' ikke er satt."
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
                      "text": "Punkt 1"
                    }
                  ]
                },
                {
                  "type": "list-item",
                  "children": [
                    {
                      "text": "Punkt 2"
                    }
                  ]
                }
              ]
            },
            {
              "type": "numbered-list",
              "children": [
                {
                  "type": "list-item",
                  "children": [
                    {
                      "text": "Punkt 1"
                    }
                  ]
                },
                {
                  "type": "list-item",
                  "children": [
                    {
                      "text": "Punkt 2"
                    }
                  ]
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
                      "text": "Punkt 1"
                    }
                  ]
                },
                {
                  "type": "list-item",
                  "children": [
                    {
                      "text": "Punkt 2"
                    }
                  ]
                }
              ]
            },
            {
              "type": "numbered-list",
              "children": [
                {
                  "type": "list-item",
                  "children": [
                    {
                      "text": "Punkt 1"
                    }
                  ]
                },
                {
                  "type": "list-item",
                  "children": [
                    {
                      "text": "Punkt 2"
                    }
                  ]
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
                      "text": "Punkt 1"
                    }
                  ]
                },
                {
                  "type": "list-item",
                  "children": [
                    {
                      "text": "Punkt 2"
                    }
                  ]
                }
              ]
            },
            {
              "type": "numbered-list",
              "children": [
                {
                  "type": "list-item",
                  "children": [
                    {
                      "text": "Punkt 1"
                    }
                  ]
                },
                {
                  "type": "list-item",
                  "children": [
                    {
                      "text": "Punkt 2"
                    }
                  ]
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
                      "text": "Punkt 1"
                    }
                  ]
                },
                {
                  "type": "list-item",
                  "children": [
                    {
                      "text": "Punkt 2"
                    }
                  ]
                }
              ]
            },
            {
              "type": "numbered-list",
              "children": [
                {
                  "type": "list-item",
                  "children": [
                    {
                      "text": "Punkt 1"
                    }
                  ]
                },
                {
                  "type": "list-item",
                  "children": [
                    {
                      "text": "Punkt 2"
                    }
                  ]
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
                      "text": "Punkt 1"
                    }
                  ]
                },
                {
                  "type": "list-item",
                  "children": [
                    {
                      "text": "Punkt 2"
                    }
                  ]
                }
              ]
            },
            {
              "type": "numbered-list",
              "children": [
                {
                  "type": "list-item",
                  "children": [
                    {
                      "text": "Punkt 1"
                    }
                  ]
                },
                {
                  "type": "list-item",
                  "children": [
                    {
                      "text": "Punkt 2"
                    }
                  ]
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
                      "text": "Punkt 1"
                    }
                  ]
                },
                {
                  "type": "list-item",
                  "children": [
                    {
                      "text": "Punkt 2"
                    }
                  ]
                }
              ]
            },
            {
              "type": "numbered-list",
              "children": [
                {
                  "type": "list-item",
                  "children": [
                    {
                      "text": "Punkt 1"
                    }
                  ]
                },
                {
                  "type": "list-item",
                  "children": [
                    {
                      "text": "Punkt 2"
                    }
                  ]
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
                      "text": "Punkt 1"
                    }
                  ]
                },
                {
                  "type": "list-item",
                  "children": [
                    {
                      "text": "Punkt 2"
                    }
                  ]
                }
              ]
            },
            {
              "type": "numbered-list",
              "children": [
                {
                  "type": "list-item",
                  "children": [
                    {
                      "text": "Punkt 1"
                    }
                  ]
                },
                {
                  "type": "list-item",
                  "children": [
                    {
                      "text": "Punkt 2"
                    }
                  ]
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
                      "text": "Punkt 1"
                    }
                  ]
                },
                {
                  "type": "list-item",
                  "children": [
                    {
                      "text": "Punkt 2"
                    }
                  ]
                }
              ]
            },
            {
              "type": "numbered-list",
              "children": [
                {
                  "type": "list-item",
                  "children": [
                    {
                      "text": "Punkt 1"
                    }
                  ]
                },
                {
                  "type": "list-item",
                  "children": [
                    {
                      "text": "Punkt 2"
                    }
                  ]
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
                      "text": "Punkt 1"
                    }
                  ]
                },
                {
                  "type": "list-item",
                  "children": [
                    {
                      "text": "Punkt 2"
                    }
                  ]
                }
              ]
            },
            {
              "type": "numbered-list",
              "children": [
                {
                  "type": "list-item",
                  "children": [
                    {
                      "text": "Punkt 1"
                    }
                  ]
                },
                {
                  "type": "list-item",
                  "children": [
                    {
                      "text": "Punkt 2"
                    }
                  ]
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
                      "text": "Punkt 1"
                    }
                  ]
                },
                {
                  "type": "list-item",
                  "children": [
                    {
                      "text": "Punkt 2"
                    }
                  ]
                }
              ]
            },
            {
              "type": "numbered-list",
              "children": [
                {
                  "type": "list-item",
                  "children": [
                    {
                      "text": "Punkt 1"
                    }
                  ]
                },
                {
                  "type": "list-item",
                  "children": [
                    {
                      "text": "Punkt 2"
                    }
                  ]
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
                      "text": "Punkt 1"
                    }
                  ]
                },
                {
                  "type": "list-item",
                  "children": [
                    {
                      "text": "Punkt 2"
                    }
                  ]
                }
              ]
            },
            {
              "type": "numbered-list",
              "children": [
                {
                  "type": "list-item",
                  "children": [
                    {
                      "text": "Punkt 1"
                    }
                  ]
                },
                {
                  "type": "list-item",
                  "children": [
                    {
                      "text": "Punkt 2"
                    }
                  ]
                }
              ]
            },
            {
              "type": "table",
              "children": [
                {
                  "type": "table-row",
                  "children": [
                    {
                      "type": "table-cell",
                      "children": [
                        {
                          "text": "test"
                        }
                      ]
                    },
                    {
                      "type": "table-cell",
                      "children": [
                        {
                          "text": "test"
                        }
                      ]
                    },
                    {
                      "type": "table-cell",
                      "children": [
                        {
                          "text": "test"
                        }
                      ]
                    }
                  ]
                },
                {
                  "type": "table-row",
                  "children": [
                    {
                      "type": "table-cell",
                      "children": [
                        {
                          "text": "test"
                        }
                      ]
                    },
                    {
                      "type": "table-cell",
                      "children": [
                        {
                          "text": "test"
                        }
                      ]
                    },
                    {
                      "type": "table-cell",
                      "children": [
                        {
                          "text": "test"
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
    },
    {
      "id": "signature",
      "label": "Signatur",
      "type": "signature",
      "content": {
        "medunderskriver": {
          "name": "Medunderskriver Navnesen",
          "title": "Medunderskriver"
        },
        "saksbehandler": {
          "name": "Saksbehandler Navnesen",
          "title": "Saksbehandler"
        }
      }
    }
  ]
}
""".trimIndent()
