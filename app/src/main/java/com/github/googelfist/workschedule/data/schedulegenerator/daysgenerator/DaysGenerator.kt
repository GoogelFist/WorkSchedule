package com.github.googelfist.workschedule.data.schedulegenerator.daysgenerator

import com.github.googelfist.workschedule.domain.models.days.Day
import java.time.LocalDate

interface DaysGenerator {

    fun generateMonthDays(activeDate: LocalDate): List<Day>
}
