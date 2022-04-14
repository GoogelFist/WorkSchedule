package com.github.googelfist.workshedule.domain.usecases

import com.github.googelfist.workshedule.domain.MonthGenerator
import com.github.googelfist.workshedule.domain.models.Month
import javax.inject.Inject

class GenerateNextMonthDefaultUseCase @Inject constructor(private val monthGenerator: MonthGenerator) {
    operator fun invoke(): Month {
        return monthGenerator.generateNextMonth()
    }
}