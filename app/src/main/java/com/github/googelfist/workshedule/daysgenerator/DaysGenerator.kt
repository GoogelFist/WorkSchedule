package com.github.googelfist.workshedule.daysgenerator

import com.github.googelfist.workshedule.domain.models.Day
import java.time.LocalDate

interface DaysGenerator {
    fun generateDays(date: LocalDate): List<Day>
}