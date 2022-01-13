package com.github.googelfist.workschedule.data.schedulesgenerator.daysfabric

import com.github.googelfist.workschedule.domain.models.days.Day
import java.time.LocalDate

interface DaysFabric {
    fun getDay(firstDate: LocalDate, date: LocalDate): Day
}
