package com.github.googelfist.workschedule.domain.schedulegenerator.daysgenerator.fabric

import com.github.googelfist.workschedule.domain.schedulegenerator.models.Day
import java.time.LocalDate

interface DaysFabric {

    fun getDay(dateOfMonth: LocalDate, activeDate: LocalDate): Day
}
