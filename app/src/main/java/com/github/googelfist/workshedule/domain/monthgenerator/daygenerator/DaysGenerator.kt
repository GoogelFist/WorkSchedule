package com.github.googelfist.workshedule.domain.monthgenerator.daygenerator

import com.github.googelfist.workshedule.domain.models.day.Day
import java.time.LocalDate

interface DaysGenerator {
    suspend fun getDays(date: LocalDate): List<Day>
}