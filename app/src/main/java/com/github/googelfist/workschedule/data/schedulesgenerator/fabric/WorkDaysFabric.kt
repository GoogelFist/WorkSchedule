package com.github.googelfist.workschedule.data.schedulesgenerator.fabric

import com.github.googelfist.workschedule.data.schedulesgenerator.scheduletype.ScheduleType
import com.github.googelfist.workschedule.domain.models.days.Day
import java.time.LocalDate

interface WorkDaysFabric {
    fun getWorkDay(
        dateInMonth: LocalDate,
        activeDate: LocalDate,
        firstWorkDate: LocalDate,
        scheduleType: ScheduleType
    ): Day
}
