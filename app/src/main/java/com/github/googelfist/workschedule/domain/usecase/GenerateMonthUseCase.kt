package com.github.googelfist.workschedule.domain.usecase

import com.github.googelfist.workschedule.domain.SchedulesGenerator
import com.github.googelfist.workschedule.domain.models.MonthDTO
import java.time.LocalDate

class GenerateMonthUseCase(
    private val schedulesGenerator: SchedulesGenerator,
    private val formatDateUseCase: FormatDateUseCase
) {

    operator fun invoke(date: LocalDate): MonthDTO {
        val currentMonth = schedulesGenerator.generateScheduleDays(date)
        val formatCurrentDate = formatDateUseCase(date)

        return MonthDTO(formatCurrentDate, date, currentMonth)
    }
}
