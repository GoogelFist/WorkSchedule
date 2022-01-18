package com.github.googelfist.workschedule.data.schedulesgenerator.formatter

import java.time.LocalDate

interface DateFormatter {

    fun format(date: LocalDate): String
}
