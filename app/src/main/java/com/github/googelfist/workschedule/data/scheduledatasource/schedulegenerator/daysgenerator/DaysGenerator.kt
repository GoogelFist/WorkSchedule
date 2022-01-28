package com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.daysgenerator

import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.models.Day
import java.time.LocalDate

interface DaysGenerator {

    fun generateMonthDays(activeDate: LocalDate): List<Day>
}
