package com.github.googelfist.workshedule.domain.usecases

import com.github.googelfist.workshedule.domain.models.month.Month
import com.github.googelfist.workshedule.domain.monthgenerator.MonthGenerator
import javax.inject.Inject

class GenerateNextMonthUseCase @Inject constructor(private val workDaysGenerator: MonthGenerator) {
    suspend operator fun invoke(): Month {
        return workDaysGenerator.generateNextMonth()
    }
}