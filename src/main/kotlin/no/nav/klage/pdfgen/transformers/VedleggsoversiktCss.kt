package no.nav.klage.pdfgen.transformers

import org.intellij.lang.annotations.Language

@Language("css")
fun getVedleggsoversiktCss() = """
    html {
        font-family: "Source Sans Pro" !important;
        box-sizing: border-box;
    }

    *, ::before, ::after {
      box-sizing: inherit;
      padding: 0;
      margin: 0;
    }

    @page { size: A4; }

    h1 {
        font-weight: 600;
        font-size: 16pt;
    }

    table {
        margin-top: 24pt;
        border-collapse: collapse;
        width: 100%;
        -fs-table-paginate: paginate;
        font-size: 12pt;
    }

    thead {
        background-color: #E0E3E6;
    }

    td, th {
        padding: 8pt;
        word-break: keep-all;
        white-space: normal;
    }
    
    td {
        padding-bottom: 0;
    }

    .odd {
        background-color: #ECEEF0;
    }

    .white-space-no-wrap {
        white-space: nowrap;
    }

    .saksnummer {
        display: inline-block;
        min-width: 35pt;
    }

    label {
        font-weight: 600;
        margin-left: 8pt;
    }

    label:first-child {
        margin-left: 0;
    }

    .extra-row {
        font-size: 10pt;
    }

    .extra-row td {
        padding-top: 4pt;
        padding-bottom: 8pt;
    }

    .bold {
        font-weight: 600;
    }
    """.trimIndent() 