package com.github.googelfist.workschedule.domain.usecase

import com.github.googelfist.workschedule.domain.ScheduleGenerator
import com.github.googelfist.workschedule.domain.models.MonthDTO
import java.time.LocalDate

class GenerateDefaultMontUseCaseImpl(
    private val scheduleGenerator: ScheduleGenerator,
    private val formatDateUseCase: FormatDateUseCase,
) : GenerateMonthUseCase {

    override fun generate(date: LocalDate): MonthDTO {
        val currentMonth = scheduleGenerator.generateDefaultSchedule(date)
        val formatCurrentDate = formatDateUseCase(date)

        return MonthDTO(formatCurrentDate, date, currentMonth)
    }
}
