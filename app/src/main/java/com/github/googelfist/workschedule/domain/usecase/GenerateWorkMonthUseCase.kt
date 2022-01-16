package com.github.googelfist.workschedule.domain.usecase

import com.github.googelfist.workschedule.domain.ScheduleGenerator
import com.github.googelfist.workschedule.domain.models.MonthDTO
import java.time.LocalDate

class GenerateWorkMonthUseCase(
    private val scheduleGenerator: ScheduleGenerator,
    private val formatDateUseCase: FormatDateUseCase,
    private val loadPreferencesUseCase: LoadPreferencesUseCase,
    private val savePreferenceUseCase: SavePreferenceUseCase,
    private val getActualDateFirstWorkUseCase: GetActualDateFirstWorkUseCase
) {

    operator fun invoke(date: LocalDate): MonthDTO {
        val preference = loadPreferencesUseCase()
        val actualFirstWorkDate = getActualDateFirstWorkUseCase(date, preference)
        savePreferenceUseCase(preference.copy(actualFirstWorkDate = actualFirstWorkDate.toString()))

        val currentMonth = scheduleGenerator.generateSchedule(date, actualFirstWorkDate)
        val formatCurrentDate = formatDateUseCase(date)

        return MonthDTO(formatCurrentDate, date, currentMonth)
    }
}
