package com.github.googelfist.workshedule.domain.daysgenerator

import com.github.googelfist.workshedule.domain.models.Day
import java.time.LocalDate

interface DaysGenerator {

    fun generateDays(date: LocalDate): List<Day>
}