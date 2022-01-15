package com.github.googelfist.workschedule.domain.usecase

import com.github.googelfist.workschedule.domain.models.MonthDTO

class GenerateNextWorkMonthUseCase(
    private val generateWorkMonthUseCase: GenerateWorkMonthUseCase,
) {

    operator fun invoke(monthDTO: MonthDTO): MonthDTO {
        val nextMonthDate = monthDTO.date.plusMonths(VALUE)
        return generateWorkMonthUseCase(nextMonthDate)
    }

    companion object {
        private const val VALUE = 1L
    }
}
