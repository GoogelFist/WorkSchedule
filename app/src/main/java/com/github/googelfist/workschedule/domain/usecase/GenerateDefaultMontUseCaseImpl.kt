package com.github.googelfist.workschedule.domain.usecase

import com.github.googelfist.workschedule.domain.DefaultScheduleGenerator
import com.github.googelfist.workschedule.domain.models.MonthDTO
import java.time.LocalDate

class GenerateDefaultMontUseCaseImpl(
    private val scheduleGenerator: DefaultScheduleGenerator,
    private val formatDateUseCase: FormatDateUseCase,
) : GenerateMonthUseCase {

    override fun generate(date: LocalDate): MonthDTO {
        val currentMonth = scheduleGenerator.generateSchedule(date)
        val formatCurrentDate = formatDateUseCase(date)

        return MonthDTO(formatCurrentDate, date, currentMonth)
    }
}
