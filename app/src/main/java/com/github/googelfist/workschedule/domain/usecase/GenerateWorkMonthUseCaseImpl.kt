package com.github.googelfist.workschedule.domain.usecase

import com.github.googelfist.workschedule.domain.ScheduleGenerator
import com.github.googelfist.workschedule.domain.models.MonthDTO
import com.github.googelfist.workschedule.domain.usecase.preference.LoadPreferencesUseCase
import com.github.googelfist.workschedule.domain.usecase.preference.SavePreferenceUseCase
import java.time.LocalDate

class GenerateWorkMonthUseCaseImpl(
    private val scheduleGenerator: ScheduleGenerator,
    private val formatDateUseCase: FormatDateUseCase,
    private val loadPreferencesUseCase: LoadPreferencesUseCase,
    private val savePreferenceUseCase: SavePreferenceUseCase,
    private val getActualDateFirstWorkUseCase: GetActualDateFirstWorkUseCase
) : GenerateMonthUseCase {

    override fun generate(date: LocalDate): MonthDTO {
        val preference = loadPreferencesUseCase()
        val actualFirstWorkDate = getActualDateFirstWorkUseCase(date, preference)
        savePreferenceUseCase(preference.copy(actualFirstWorkDate = actualFirstWorkDate.toString()))

        val currentMonth = scheduleGenerator.generateSchedule(date, actualFirstWorkDate)
        val formatCurrentDate = formatDateUseCase(date)

        return MonthDTO(formatCurrentDate, date, currentMonth)
    }
}