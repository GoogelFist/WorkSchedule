package com.github.googelfist.workshedule.domain.usecase

import com.github.googelfist.workshedule.domain.SchedulesGenerator
import com.github.googelfist.workshedule.domain.models.MonthDTO
import java.time.LocalDate

class GenerateWorkMonthUseCase(
    private val schedulesGenerator: SchedulesGenerator,
    private val formatDateUseCase: FormatDateUseCase
) {

    operator fun invoke(date: LocalDate, firstWorkDate: LocalDate, step: Int): MonthDTO {
        val currentMonth = schedulesGenerator.generateScheduleWorkDays(date, firstWorkDate, step)
        val formatCurrentDate = formatDateUseCase(date)

        return MonthDTO(formatCurrentDate, date, currentMonth)
    }
}
