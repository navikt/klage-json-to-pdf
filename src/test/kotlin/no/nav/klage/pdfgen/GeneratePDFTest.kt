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
  "title": "Tittel for vedtaksmalen",
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
      "label": "Prosessfullmektig",
      "content": ""
    },
    {
      "id": "about",
      "type": "text",
      "label": "Saken gjelder",
      "content": "",
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
              "text": ""
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
                  "text": "Dette er en standardtekst."
                }
              ]
            },
            {
              "type": "h1",
              "children": [
                {
                  "text": "Tittel for vedtaket"
                }
              ]
            },
            {
              "type": "h2",
              "children": [
                {
                  "text": "Tittel for vedtaket"
                }
              ]
            },
            {
              "type": "h3",
              "children": [
                {
                  "text": "Tittel for vedtaket"
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
              "type": "paragraph",
              "children": [
                {
                  "text": "En paragraf med "
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
              "type": "ul",
              "children": [
                {
                  "type": "li",
                  "children": [
                    {
                      "text": "Punkt 1"
                    }
                  ]
                },
                {
                  "type": "li",
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
                  "type": "tr",
                  "children": [
                    {
                      "type": "td",
                      "children": [
                        {
                          "text": "test"
                        }
                      ]
                    },
                    {
                      "type": "td",
                      "children": [
                        {
                          "text": "test"
                        }
                      ]
                    },
                    {
                      "type": "td",
                      "children": [
                        {
                          "text": "test"
                        }
                      ]
                    }
                  ]
                },
                {
                  "type": "tr",
                  "children": [
                    {
                      "type": "td",
                      "children": [
                        {
                          "text": "test"
                        }
                      ]
                    },
                    {
                      "type": "td",
                      "children": [
                        {
                          "text": "test"
                        }
                      ]
                    },
                    {
                      "type": "td",
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
    }
  ]
}
""".trimIndent()

