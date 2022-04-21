package com.github.googelfist.workshedule.domain.monthgenerator

import com.github.googelfist.workshedule.domain.models.month.Month

interface MonthGenerator {
    suspend fun generateCurrentMonth(): Month
    suspend fun generatePreviousMonth(): Month
    suspend fun generateNextMonth(): Month
}