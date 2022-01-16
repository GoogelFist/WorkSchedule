package com.github.googelfist.workschedule.domain.usecase

import com.github.googelfist.workschedule.domain.models.MonthDTO

class GeneratePreviousMonthUseCase(
    private val generateMonthUseCase: GenerateMonthUseCase,
) {

    operator fun invoke(monthDTO: MonthDTO): MonthDTO {
        val previousMonthDate = monthDTO.date.minusMonths(VALUE)
        return generateMonthUseCase.generate(previousMonthDate)
    }

    companion object {
        private const val VALUE = 1L
    }
}
