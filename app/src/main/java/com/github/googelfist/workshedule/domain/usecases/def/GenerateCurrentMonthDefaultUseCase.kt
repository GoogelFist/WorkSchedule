package com.github.googelfist.workshedule.domain.usecases.def

import com.github.googelfist.workshedule.domain.models.month.Month
import com.github.googelfist.workshedule.domain.monthgenerator.def.DefaultMonthGenerator
import javax.inject.Inject

class GenerateCurrentMonthDefaultUseCase @Inject constructor(private val defaultMonthGenerator: DefaultMonthGenerator) {
    operator fun invoke(): Month {
        return defaultMonthGenerator.generateCurrentMonth()
    }
}