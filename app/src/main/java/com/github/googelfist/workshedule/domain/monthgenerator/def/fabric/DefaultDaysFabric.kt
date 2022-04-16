package com.github.googelfist.workshedule.domain.monthgenerator.def.fabric

import com.github.googelfist.workshedule.domain.models.day.Day
import java.time.LocalDate

interface DefaultDaysFabric {
    fun getDefaultDay(dateOfMonth: LocalDate, activeDate: LocalDate): Day

    fun getTwoInTwoWorkDay(
        dateOfMonth: LocalDate,
        activeDate: LocalDate,
        workSchedule: Set<LocalDate>
    ): Day
}