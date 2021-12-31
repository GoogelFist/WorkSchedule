package com.github.googelfist.workshedule.data

import com.github.googelfist.workshedule.domain.Day
import java.time.LocalDate

interface DaysGenerator {
    fun generateDays(date: LocalDate): List<Day>

    fun generateDays(date: LocalDate, startDay: LocalDate): List<Day>
}