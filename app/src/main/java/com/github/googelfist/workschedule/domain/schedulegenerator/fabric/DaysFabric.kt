package com.github.googelfist.workschedule.domain.schedulegenerator.fabric

import com.github.googelfist.workschedule.domain.schedulegenerator.models.Day
import java.time.LocalDate

interface DaysFabric {

    fun getDay(dateInMonth: LocalDate, activeDate: LocalDate): Day
}
