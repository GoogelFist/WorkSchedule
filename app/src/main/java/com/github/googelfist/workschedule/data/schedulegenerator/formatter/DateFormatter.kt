package com.github.googelfist.workschedule.data.schedulegenerator.formatter

import java.time.LocalDate

interface DateFormatter {

    fun format(date: LocalDate): String
}
