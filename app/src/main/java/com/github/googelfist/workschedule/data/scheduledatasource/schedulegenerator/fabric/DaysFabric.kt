package com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.fabric

import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.models.Day
import java.time.LocalDate

interface DaysFabric {

    fun getDay(dateInMonth: LocalDate, activeDate: LocalDate): Day
}
