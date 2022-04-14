package com.github.googelfist.workshedule.domain.formatter

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

class DateFormatterImpl @Inject constructor() : DateFormatter {
    override fun formatDate(date: LocalDate): String {
        return DateTimeFormatter.ofPattern(FORMAT_PATTERN, Locale.ENGLISH).format(date)
    }

    companion object {
        private const val FORMAT_PATTERN = "MMMM yyyy"
    }
}