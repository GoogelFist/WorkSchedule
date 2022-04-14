package com.github.googelfist.workshedule.domain

import com.github.googelfist.workshedule.domain.models.Month

interface MonthGenerator {
    fun generateCurrentMonth(): Month
    fun generatePreviousMonth(): Month
    fun generateNextMonth(): Month
}