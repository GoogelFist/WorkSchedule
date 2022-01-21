package com.github.googelfist.workschedule.data.generator.formatter

import java.time.LocalDate

interface DateFormatter {

    fun format(date: LocalDate): String
}
