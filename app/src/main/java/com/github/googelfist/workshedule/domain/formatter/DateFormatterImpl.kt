package com.github.googelfist.workshedule.domain.formatter

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

class DateFormatterImpl @Inject constructor() : DateFormatter {
    override fun formatDateToSchedule(date: LocalDate): String {
        return DateTimeFormatter.ofPattern(SCHEDULE_FORMAT_PATTERN, Locale.ENGLISH).format(date)
    }

    override fun formatDateToConfig(date: LocalDate): String {
        return DateTimeFormatter.ofPattern(CONFIG_FORMAT_PATTERN, Locale.ENGLISH).format(date)
    }

    companion object {
        private const val SCHEDULE_FORMAT_PATTERN = "MMMM yyyy"
        private const val CONFIG_FORMAT_PATTERN = "dd MMMM yyyy"
    }
}