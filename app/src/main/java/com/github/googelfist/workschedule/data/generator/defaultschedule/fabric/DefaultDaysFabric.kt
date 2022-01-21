package com.github.googelfist.workschedule.data.generator.defaultschedule.fabric

import com.github.googelfist.workschedule.domain.models.days.Day
import java.time.LocalDate

interface DefaultDaysFabric {

    fun getDefaultDay(dateInMonth: LocalDate, activeDate: LocalDate): Day
}
