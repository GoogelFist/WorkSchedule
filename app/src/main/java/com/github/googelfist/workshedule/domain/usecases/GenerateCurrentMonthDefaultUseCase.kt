package com.github.googelfist.workshedule.domain.usecases

import com.github.googelfist.workshedule.domain.MonthGenerator
import com.github.googelfist.workshedule.domain.models.Month
import javax.inject.Inject

class GenerateCurrentMonthDefaultUseCase @Inject constructor(private val monthGenerator: MonthGenerator) {
    operator fun invoke(): Month {
        return monthGenerator.generateCurrentMonth()
    }
}