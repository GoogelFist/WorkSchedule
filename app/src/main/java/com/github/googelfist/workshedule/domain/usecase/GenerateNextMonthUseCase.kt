package com.github.googelfist.workshedule.domain.usecase

import com.github.googelfist.workshedule.domain.models.MonthDTO

class GenerateNextMonthUseCase(private val generateMonthUseCase: GenerateMonthUseCase) {

    operator fun invoke(monthDTO: MonthDTO): MonthDTO {
        val nextMonthDate = monthDTO.date.plusMonths(VALUE)
        return generateMonthUseCase(nextMonthDate)
    }

    companion object {
        private const val VALUE = 1L
    }
}
