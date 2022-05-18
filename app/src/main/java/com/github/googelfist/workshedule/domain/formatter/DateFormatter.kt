package com.github.googelfist.workshedule.domain.formatter

import java.time.LocalDate

interface DateFormatter {

    fun formatDateToSchedule(date: LocalDate): String

    fun formatDateToConfig(date: LocalDate): String
}