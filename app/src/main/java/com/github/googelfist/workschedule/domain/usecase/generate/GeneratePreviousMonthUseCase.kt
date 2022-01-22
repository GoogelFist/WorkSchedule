package com.github.googelfist.workschedule.domain.usecase.generate

import androidx.lifecycle.LiveData
import com.github.googelfist.workschedule.domain.ScheduleGenerator
import com.github.googelfist.workschedule.domain.models.days.Day

class GeneratePreviousMonthUseCase(private val scheduleGenerator: ScheduleGenerator) {

    operator fun invoke(): LiveData<List<Day>> {
        return scheduleGenerator.generatePreviousSchedule()
    }
}
