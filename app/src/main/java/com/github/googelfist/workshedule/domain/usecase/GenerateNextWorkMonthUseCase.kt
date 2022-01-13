package com.github.googelfist.workshedule.domain.usecase

import com.github.googelfist.workshedule.domain.models.MonthDTO
import java.time.LocalDate

class GenerateNextWorkMonthUseCase(
    private val generateWorkMonthUseCase: GenerateWorkMonthUseCase,
    private val getActualDateFirstWorkUseCase: GetActualDateFirstWorkUseCase
) {

    operator fun invoke(monthDTO: MonthDTO, firstWorkDate: LocalDate, step: Int): MonthDTO {
        val nextMonthDate = monthDTO.date.plusMonths(VALUE)
        val actualDateFirstWork =
            getActualDateFirstWorkUseCase(nextMonthDate, firstWorkDate, step)
        return generateWorkMonthUseCase(nextMonthDate, actualDateFirstWork, step)
    }

    companion object {
        private const val VALUE = 1L
    }
}
