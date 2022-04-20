package com.github.googelfist.workshedule.domain.monthgenerator.twointwo

import com.github.googelfist.workshedule.domain.models.month.Month
import java.time.LocalDate

interface WorkTwoInTwoMonthGenerator {
    fun generateCurrentMonth(firstWorkDate: LocalDate): Month
    fun generatePreviousMonth(firstWorkDate: LocalDate): Month
    fun generateNextMonth(firstWorkDate: LocalDate): Month
}