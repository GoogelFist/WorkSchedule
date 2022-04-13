package com.github.googelfist.workschedule.domain.schedulegenerator.daysgenerator.fabric

import com.github.googelfist.workschedule.domain.schedulegenerator.models.Day
import java.time.LocalDate

interface WorkDaysFabric {
    fun getWorkDay(
        dateInMonth: LocalDate,
        activeDate: LocalDate,
        firstWorkDay: LocalDate,
        actualFirstWorkDay: LocalDate,
        workSchedule: Set<LocalDate>
    ): Day
}
