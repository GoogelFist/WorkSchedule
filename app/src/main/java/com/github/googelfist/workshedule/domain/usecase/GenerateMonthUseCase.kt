package com.github.googelfist.workshedule.domain.usecase

import com.github.googelfist.workshedule.domain.SchedulesGenerator
import com.github.googelfist.workshedule.domain.models.MonthDTO
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
