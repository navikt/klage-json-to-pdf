package no.nav.klage.pdfgen.util

import java.time.format.DateTimeFormatter
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*

 fun getCurrentDate(): String {
  val formatter = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.forLanguageTag("no"))
  val dateAsText = ZonedDateTime.now(ZoneId.of("Europe/Oslo")).format(formatter)   
  
  return "Dato: $dateAsText"
}