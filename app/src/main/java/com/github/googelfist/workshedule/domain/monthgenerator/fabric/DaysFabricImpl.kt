package com.github.googelfist.workshedule.domain.monthgenerator.fabric

import com.github.googelfist.workshedule.domain.models.day.Day
import java.time.LocalDate
import javax.inject.Inject

class DaysFabricImpl @Inject constructor(
    private val defaultDaysFabric: DefaultDaysFabric,
    private val workDaysFabric: WorkDaysFabric
) : DaysFabric {

    override fun getDefaultDay(dateOfMonth: LocalDate, activeDate: LocalDate): Day {
        return defaultDaysFabric.getDay(dateOfMonth, activeDate)
    }

    override fun getWorkDay(
        dateOfMonth: LocalDate,
        activeDate: LocalDate,
        workSchedule: Map<LocalDate, Int>
    ): Day {
        return workDaysFabric.getDay(dateOfMonth, activeDate, workSchedule)
    }
}