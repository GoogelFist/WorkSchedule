package com.github.googelfist.workschedule.domain.usecase.generate

import androidx.lifecycle.LiveData
import com.github.googelfist.workschedule.data.schedulegenerator.models.Day
import com.github.googelfist.workschedule.domain.ScheduleGenerator

class GenerateCurrentMonthUseCase(private val scheduleGenerator: ScheduleGenerator) {

    operator fun invoke(): LiveData<List<Day>> {
        return scheduleGenerator.generateCurrentSchedule()
    }
}
