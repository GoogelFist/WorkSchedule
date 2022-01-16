package com.github.googelfist.workschedule.data.defaultschedulegenerator.fabric

import com.github.googelfist.workschedule.domain.models.days.Day
import java.time.LocalDate

interface DefaultDaysFabric {

    fun getDay(dateInMonth: LocalDate, activeDate: LocalDate): Day
}
