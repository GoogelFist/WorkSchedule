package com.github.googelfist.workschedule.data.schedulesgenerator.formatter

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DateFormatterImpl : DateFormatter {

    override fun format(date: LocalDate): String {
        return DateTimeFormatter.ofPattern(FORMAT_PATTERN).format(date)
    }

    companion object {
        private const val FORMAT_PATTERN = "MMM yyyy"
    }
}
