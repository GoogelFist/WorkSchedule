package com.github.googelfist.workshedule.domain.usecase

import com.github.googelfist.workshedule.domain.DaysGenerator
import com.github.googelfist.workshedule.domain.WorkDaysGenerator
import com.github.googelfist.workshedule.domain.models.Month
import java.time.LocalDate

class GenerateWorkMonthUseCase(
    private val workDaysGenerator: WorkDaysGenerator,
    private val formatDateUseCase: FormatDateUseCase
) {

    fun generate(date: LocalDate, firstWorkDate: LocalDate, step: Int): Month {
        val currentMonth = workDaysGenerator.generateWorkDays(date, firstWorkDate, step)
        val formatCurrentDate = formatDateUseCase.formatDate(date)

        return Month(formatCurrentDate, date, currentMonth)
    }
}