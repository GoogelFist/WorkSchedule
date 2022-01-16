package com.github.googelfist.workschedule.data.schedulesgenerator.daysgenerator

import com.github.googelfist.workschedule.domain.models.days.Day
import java.time.LocalDate

interface DaysGenerator {
    fun generateDays(activeDate: LocalDate, firstWorkDate: LocalDate): List<Day>
}
