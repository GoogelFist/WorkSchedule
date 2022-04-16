package com.github.googelfist.workshedule.domain.monthgenerator.twointwo.fabric

import com.github.googelfist.workshedule.domain.models.day.Day
import java.time.LocalDate

interface WorkTwoInTwoDaysFabric {
    fun getWorkDay(
        dateOfMonth: LocalDate,
        activeDate: LocalDate,
        workSchedule: Set<LocalDate>
    ): Day
}