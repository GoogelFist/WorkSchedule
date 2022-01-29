package com.github.googelfist.workschedule.domain.schedulegenerator.daysgenerator

import com.github.googelfist.workschedule.domain.schedulegenerator.models.Day
import java.time.LocalDate

interface DaysGenerator {

    fun generateMonthDays(activeDate: LocalDate): List<Day>
}
