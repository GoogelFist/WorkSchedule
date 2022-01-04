package com.github.googelfist.workshedule.domain.usecase

import com.github.googelfist.workshedule.domain.DaysGenerator
import com.github.googelfist.workshedule.domain.models.Month
import java.time.LocalDate

class GenerateMonthUseCase(
    private val daysGenerator: DaysGenerator,
    private val formatDateUseCase: FormatDateUseCase
) {

    fun generate(date: LocalDate): Month {
        val currentMonth = daysGenerator.generateDays(date)
        val formatCurrentDate = formatDateUseCase.formatDate(date)

        return Month(formatCurrentDate, date, currentMonth)
    }
}