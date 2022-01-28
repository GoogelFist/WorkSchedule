package com.github.googelfist.workschedule.domain.usecase.generate

import androidx.lifecycle.LiveData
import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.models.Day
import com.github.googelfist.workschedule.domain.ScheduleRepository
import javax.inject.Inject

class GeneratePreviousMonthUseCase @Inject constructor(private val repository: ScheduleRepository) {

    operator fun invoke(): LiveData<List<Day>> {
        return repository.generatePreviousSchedule()
    }
}
