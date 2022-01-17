package com.github.googelfist.workschedule.data.schedulesgenerator.work.fabric

import com.github.googelfist.workschedule.domain.models.days.Day
import java.time.LocalDate

interface WorkDaysFabric {
    fun getDay(dateInMonth: LocalDate, activeDate: LocalDate, firstWorkDate: LocalDate): Day
}
