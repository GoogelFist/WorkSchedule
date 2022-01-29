package com.github.googelfist.workschedule.domain.usecase.generate

import androidx.lifecycle.LiveData
import com.github.googelfist.workschedule.domain.ScheduleGenerator
import com.github.googelfist.workschedule.domain.schedulegenerator.models.Day
import javax.inject.Inject

class GeneratePreviousMonthUseCase @Inject constructor(private val generator: ScheduleGenerator) {

    operator fun invoke(): LiveData<List<Day>> {
        return generator.generatePreviousSchedule()
    }
}
