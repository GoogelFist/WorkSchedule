package com.github.googelfist.workshedule.domain.monthgenerator.twointwo

import com.github.googelfist.workshedule.domain.models.month.Month

interface WorkTwoInTwoMonthGenerator {
    fun generateCurrentMonth(): Month
    fun generatePreviousMonth(): Month
    fun generateNextMonth(): Month
}