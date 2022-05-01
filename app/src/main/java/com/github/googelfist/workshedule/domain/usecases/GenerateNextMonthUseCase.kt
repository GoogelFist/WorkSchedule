package com.github.googelfist.workshedule.domain.usecases

import com.github.googelfist.workshedule.domain.models.ScheduleState
import com.github.googelfist.workshedule.domain.monthgenerator.ScheduleGenerator
import javax.inject.Inject

class GenerateNextMonthUseCase @Inject constructor(private val workDaysGenerator: ScheduleGenerator) {
    suspend operator fun invoke(): ScheduleState {
        return workDaysGenerator.generateNextMonth()
    }
}