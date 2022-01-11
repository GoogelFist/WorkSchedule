package com.github.googelfist.workshedule.domain.usecase

import com.github.googelfist.workshedule.domain.models.MonthDTO
import com.github.googelfist.workshedule.domain.schedulesgenerator.SchedulesGenerator
import java.time.LocalDate

class GenerateMonthUseCase(
    private val schedulesGenerator: SchedulesGenerator,
    private val formatDateUseCase: FormatDateUseCase
) {

    fun generate(date: LocalDate): MonthDTO {
        val currentMonth = schedulesGenerator.generateScheduleDays(date)
        val formatCurrentDate = formatDateUseCase.formatDate(date)

        return MonthDTO(formatCurrentDate, date, currentMonth)
    }
}
