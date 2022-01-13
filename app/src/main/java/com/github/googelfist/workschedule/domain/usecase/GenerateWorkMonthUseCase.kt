package com.github.googelfist.workschedule.domain.usecase

import com.github.googelfist.workschedule.domain.SchedulesGenerator
import com.github.googelfist.workschedule.domain.models.MonthDTO
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
