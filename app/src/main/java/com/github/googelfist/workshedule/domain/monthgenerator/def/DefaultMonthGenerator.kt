package com.github.googelfist.workshedule.domain.monthgenerator.def

import com.github.googelfist.workshedule.domain.models.month.Month

interface DefaultMonthGenerator {
    fun generateCurrentMonth(): Month
    fun generatePreviousMonth(): Month
    fun generateNextMonth(): Month
}