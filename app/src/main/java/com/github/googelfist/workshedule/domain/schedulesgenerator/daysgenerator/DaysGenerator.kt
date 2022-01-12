package com.github.googelfist.workshedule.domain.schedulesgenerator.daysgenerator

import com.github.googelfist.workshedule.domain.models.days.Day
import java.time.LocalDate

interface DaysGenerator {
    fun generateDays(date: LocalDate): List<Day>
}
