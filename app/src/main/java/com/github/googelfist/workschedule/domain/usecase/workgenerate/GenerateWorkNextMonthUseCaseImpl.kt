package com.github.googelfist.workschedule.domain.usecase.workgenerate

import androidx.lifecycle.LiveData
import com.github.googelfist.workschedule.domain.WorkScheduleGenerator
import com.github.googelfist.workschedule.domain.models.days.Day
import com.github.googelfist.workschedule.domain.usecase.GenerateMonthUseCase
import com.github.googelfist.workschedule.domain.usecase.preference.LoadPreferencesUseCase
import java.time.LocalDate

class GenerateWorkNextMonthUseCaseImpl(
    private val scheduleGenerator: WorkScheduleGenerator,
    private val loadPreferencesUseCase: LoadPreferencesUseCase
) : GenerateMonthUseCase {

    override fun invoke(): LiveData<List<Day>> {
        val preference = loadPreferencesUseCase()
        val firstWorkDate = LocalDate.parse(preference.firstWorkDate)
        return scheduleGenerator.generateWorkNexSchedule(firstWorkDate)
    }
}
