package com.github.googelfist.workschedule.domain.usecase

import com.github.googelfist.workschedule.domain.models.MonthDTO

class GeneratePreviousWorkMonthUseCase(
    private val generateWorkMonthUseCase: GenerateWorkMonthUseCase,
) {

    operator fun invoke(monthDTO: MonthDTO): MonthDTO {
        val previousMonthDate = monthDTO.date.minusMonths(VALUE)
        return generateWorkMonthUseCase(previousMonthDate)
    }

    companion object {
        private const val VALUE = 1L
    }
}
