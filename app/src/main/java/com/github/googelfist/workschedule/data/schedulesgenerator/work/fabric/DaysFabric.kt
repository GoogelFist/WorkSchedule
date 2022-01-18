package com.github.googelfist.workschedule.data.schedulesgenerator.work.fabric

import com.github.googelfist.workschedule.data.schedulesgenerator.work.scheduletype.ScheduleType
import com.github.googelfist.workschedule.domain.models.days.Day
import java.time.LocalDate

interface DaysFabric {
    fun getWorkDay(
        dateInMonth: LocalDate,
        activeDate: LocalDate,
        firstWorkDate: LocalDate,
        scheduleType: ScheduleType
    ): Day

    fun getDefaultDay(dateInMonth: LocalDate, activeDate: LocalDate): Day
}
