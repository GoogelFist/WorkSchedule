package com.github.googelfist.workshedule.domain.usecases

import com.github.googelfist.workshedule.domain.models.ScheduleState
import com.github.googelfist.workshedule.domain.monthgenerator.ScheduleGenerator
import javax.inject.Inject

class GetPreviousMonthStateUseCase @Inject constructor(private val scheduleGenerator: ScheduleGenerator) {
    suspend operator fun invoke(): ScheduleState {
        return scheduleGenerator.getPreviousMonthState()
    }
}