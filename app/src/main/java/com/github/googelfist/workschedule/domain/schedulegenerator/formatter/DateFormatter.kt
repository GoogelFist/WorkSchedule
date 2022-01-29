package com.github.googelfist.workschedule.domain.schedulegenerator.formatter

import java.time.LocalDate

interface DateFormatter {

    fun format(date: LocalDate): String
}
