package com.github.googelfist.workshedule.domain.usecase

import com.github.googelfist.workshedule.domain.models.MonthDTO

class GenerateNextMonthUseCase(private val generateMonthUseCase: GenerateMonthUseCase) {

    fun generate(monthDTO: MonthDTO): MonthDTO {
        val nextMonthDate = monthDTO.date.plusMonths(VALUE)
        return generateMonthUseCase.generate(nextMonthDate)
    }

    companion object {
        private const val VALUE = 1L
    }
}
