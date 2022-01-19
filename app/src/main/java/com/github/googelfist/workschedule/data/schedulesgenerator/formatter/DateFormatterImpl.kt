package com.github.googelfist.workschedule.data.schedulesgenerator.formatter

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class DateFormatterImpl : DateFormatter {

    override fun format(date: LocalDate): String {
        return DateTimeFormatter.ofPattern(FORMAT_PATTERN, Locale.ENGLISH).format(date)
    }

    companion object {
        private const val FORMAT_PATTERN = "MMMM yyyy"
    }
}
