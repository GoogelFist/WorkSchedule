package com.github.googelfist.workshedule.domain.usecase

import com.github.googelfist.workshedule.domain.schedulesgenerator.SchedulesGenerator
import com.github.googelfist.workshedule.domain.models.Month
import java.time.LocalDate

class GenerateMonthUseCase(
    private val schedulesGenerator: SchedulesGenerator,
    private val formatDateUseCase: FormatDateUseCase
) {

    fun generate(date: LocalDate): Month {
        val currentMonth = schedulesGenerator.generateScheduleDays(date)
        val formatCurrentDate = formatDateUseCase.formatDate(date)

        return Month(formatCurrentDate, date, currentMonth)
    }
}