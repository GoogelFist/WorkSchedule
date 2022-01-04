package com.github.googelfist.workshedule.domain.usecase

import com.github.googelfist.workshedule.domain.models.Month

class GeneratePreviousMonthUseCase(private val generateMonthUseCase: GenerateMonthUseCase) {

    fun generate(month: Month): Month {
        val nextMonthDate = month.date.minusMonths(VALUE)
        return generateMonthUseCase.generate(nextMonthDate)
    }

    companion object {
        private const val VALUE = 1L
    }
}