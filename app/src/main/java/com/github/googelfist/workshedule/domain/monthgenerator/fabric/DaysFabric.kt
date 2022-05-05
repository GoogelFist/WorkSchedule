package com.github.googelfist.workshedule.domain.monthgenerator.fabric

import com.github.googelfist.workshedule.domain.models.day.Day
import java.time.LocalDate

interface DaysFabric {
    fun getDefaultDay(dateOfMonth: LocalDate, activeDate: LocalDate): Day

    suspend fun getWorkDay(
        dateOfMonth: LocalDate,
        activeDate: LocalDate,
        workSchedule: Map<LocalDate, Int>
    ): Day
}