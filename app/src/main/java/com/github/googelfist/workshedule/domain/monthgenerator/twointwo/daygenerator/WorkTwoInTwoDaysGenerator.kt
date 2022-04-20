package com.github.googelfist.workshedule.domain.monthgenerator.twointwo.daygenerator

import com.github.googelfist.workshedule.domain.models.day.Day
import java.time.LocalDate

interface WorkTwoInTwoDaysGenerator {
    fun generateDays(date: LocalDate, firstWorkDate: LocalDate): List<Day>
}