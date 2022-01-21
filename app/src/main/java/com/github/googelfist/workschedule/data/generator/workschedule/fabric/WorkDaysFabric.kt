package com.github.googelfist.workschedule.data.generator.workschedule.fabric

import com.github.googelfist.workschedule.data.generator.scheduletype.ScheduleTyper
import com.github.googelfist.workschedule.domain.models.days.Day
import java.time.LocalDate

interface WorkDaysFabric {
    fun getWorkDay(
        dateInMonth: LocalDate,
        activeDate: LocalDate,
        firstWorkDate: LocalDate,
        scheduleTyper: ScheduleTyper
    ): Day
}
