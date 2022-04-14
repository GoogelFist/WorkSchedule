package com.github.googelfist.workshedule.domain.daysgenerator

import com.github.googelfist.workshedule.domain.models.Day
import java.time.LocalDate

interface DaysFabric {
    fun getDay(dateOfMonth: LocalDate, activeDate: LocalDate): Day
}