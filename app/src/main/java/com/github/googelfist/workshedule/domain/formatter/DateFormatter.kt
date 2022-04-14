package com.github.googelfist.workshedule.domain.formatter

import java.time.LocalDate

interface DateFormatter {

    fun formatDate(date: LocalDate): String
}