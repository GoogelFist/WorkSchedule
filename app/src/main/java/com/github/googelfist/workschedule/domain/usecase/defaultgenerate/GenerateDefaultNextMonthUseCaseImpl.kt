package com.github.googelfist.workschedule.domain.usecase.defaultgenerate

import androidx.lifecycle.LiveData
import com.github.googelfist.workschedule.domain.DefaultScheduleGenerator
import com.github.googelfist.workschedule.domain.models.days.Day
import com.github.googelfist.workschedule.domain.usecase.GenerateNextMonthUseCase

class GenerateDefaultNextMonthUseCaseImpl(
    private val scheduleGenerator: DefaultScheduleGenerator
) : GenerateNextMonthUseCase {

    override operator fun invoke(): LiveData<List<Day>> {
        return scheduleGenerator.generateNextSchedule()
    }
}
