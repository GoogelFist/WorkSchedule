package com.github.googelfist.workshedule.domain.monthgenerator.fabric

import com.github.googelfist.workshedule.domain.models.Day
import com.github.googelfist.workshedule.domain.models.DayType
import java.time.LocalDate

interface DaysFabric {

    fun getDay(dayType: DayType, dateOfMonth: LocalDate, activeDate: LocalDate): Day
}