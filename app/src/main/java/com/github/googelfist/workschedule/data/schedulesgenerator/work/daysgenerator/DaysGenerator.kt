package com.github.googelfist.workschedule.data.schedulesgenerator.work.daysgenerator

import com.github.googelfist.workschedule.domain.models.days.Day
import java.time.LocalDate

interface DaysGenerator {
    fun generateWorkDays(activeDate: LocalDate, firstWorkDate: LocalDate): List<Day>

    fun generateDefaultDays(activeDate: LocalDate): List<Day>
}
