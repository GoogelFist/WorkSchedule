package com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.formatter

import java.time.LocalDate

interface DateFormatter {

    fun format(date: LocalDate): String
}
