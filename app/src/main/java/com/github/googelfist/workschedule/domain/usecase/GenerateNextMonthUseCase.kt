package com.github.googelfist.workschedule.domain.usecase

import com.github.googelfist.workschedule.domain.models.MonthDTO

class GenerateNextMonthUseCase(private val generateMonthUseCase: GenerateMonthUseCase) {

    operator fun invoke(monthDTO: MonthDTO): MonthDTO {
        val nextMonthDate = monthDTO.date.plusMonths(VALUE)
        return generateMonthUseCase(nextMonthDate)
    }

    companion object {
        private const val VALUE = 1L
    }
}
