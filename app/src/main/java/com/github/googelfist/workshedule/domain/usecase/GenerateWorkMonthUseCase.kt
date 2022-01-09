package com.github.googelfist.workshedule.domain.usecase

import com.github.googelfist.workshedule.domain.schedulesgenerator.SchedulesGenerator
import com.github.googelfist.workshedule.domain.models.Month
import java.time.LocalDate

class GenerateWorkMonthUseCase(
    private val schedulesGenerator: SchedulesGenerator,
    private val formatDateUseCase: FormatDateUseCase
) {

    fun generate(date: LocalDate, firstWorkDate: LocalDate, step: Int): Month {
        val currentMonth = schedulesGenerator.generateScheduleWorkDays(date, firstWorkDate, step)
        val formatCurrentDate = formatDateUseCase.formatDate(date)

        return Month(formatCurrentDate, date, currentMonth)
    }
}