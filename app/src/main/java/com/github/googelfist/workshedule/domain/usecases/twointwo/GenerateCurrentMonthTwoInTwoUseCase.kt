package com.github.googelfist.workshedule.domain.usecases.twointwo

import com.github.googelfist.workshedule.domain.models.month.Month
import com.github.googelfist.workshedule.domain.monthgenerator.twointwo.WorkTwoInTwoMonthGenerator
import javax.inject.Inject

class GenerateCurrentMonthTwoInTwoUseCase @Inject constructor(private val workDaysGenerator: WorkTwoInTwoMonthGenerator) {
    operator fun invoke(): Month {
        return workDaysGenerator.generateCurrentMonth()
    }
}