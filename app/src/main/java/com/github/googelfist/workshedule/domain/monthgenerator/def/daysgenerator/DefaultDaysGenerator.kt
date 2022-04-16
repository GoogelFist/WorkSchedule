package com.github.googelfist.workshedule.domain.monthgenerator.def.daysgenerator

import com.github.googelfist.workshedule.domain.models.day.Day
import java.time.LocalDate

interface DefaultDaysGenerator {
    fun generateDays(date: LocalDate): List<Day>
}