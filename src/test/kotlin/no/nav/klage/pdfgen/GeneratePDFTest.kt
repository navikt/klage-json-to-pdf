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
    "section": "section-esel",
    "children": [
      {
        "text": ""
      }
    ],
    "content": null,
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
    "type": "redigerbar-maltekst",
    "section": "section-rev",
    "children": [
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
  },
  {
    "type": "maltekst",
    "section": "section-mår",
    "children": [
      {
        "text": ""
      }
    ],
    "content": null,
    "threadIds": []
  },
  {
    "type": "redigerbar-maltekst",
    "section": "section-mår",
    "children": [
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
  },
  {
    "type": "redigerbar-maltekst",
    "section": "section-ulv",
    "children": [
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
  },
  {
    "type": "redigerbar-maltekst",
    "section": "section-sau",
    "children": [
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
  },
  {
    "type": "maltekst",
    "section": "section-mus",
    "children": [
      {
        "text": ""
      }
    ],
    "content": null,
    "threadIds": []
  },
  {
    "type": "redigerbar-maltekst",
    "section": "section-mus",
    "children": [
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
  },
  {
    "type": "maltekst",
    "section": "section-elg",
    "children": [
      {
        "text": ""
      }
    ],
    "content": null,
    "threadIds": []
  },
  {
    "type": "redigerbar-maltekst",
    "section": "section-elg",
    "children": [
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
  },
  {
    "type": "maltekst",
    "section": "section-ape",
    "children": [
      {
        "text": ""
      }
    ],
    "content": null,
    "threadIds": []
  },
  {
    "type": "redigerbar-maltekst",
    "section": "section-ape",
    "children": [
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
  },
  {
    "type": "maltekst",
    "section": "section-gris",
    "children": [
      {
        "text": ""
      }
    ],
    "content": null,
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
    "content": null,
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
    "content": null,
    "threadIds": []
  },
  {
    "type": "regelverktekst",
    "section": "section-gnu",
    "children": [
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
  }
]
""".trimIndent()
