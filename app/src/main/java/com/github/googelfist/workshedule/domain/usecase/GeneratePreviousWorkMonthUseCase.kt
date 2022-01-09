package com.github.googelfist.workshedule.domain.usecase

import com.github.googelfist.workshedule.domain.models.MonthDTO
import java.time.LocalDate

class GeneratePreviousWorkMonthUseCase(
    private val generateWorkMonthUseCase: GenerateWorkMonthUseCase,
    private val getActualDateFirstWorkUseCase: GetActualDateFirstWorkUseCase
) {

    fun generate(monthDTO: MonthDTO, firstWorkDate: LocalDate, step: Int): MonthDTO {
        val previousMonthDate = monthDTO.date.minusMonths(VALUE)
        val actualDateFirstWork = getActualDateFirstWorkUseCase.getActualDateFirstWork(
            previousMonthDate,
            firstWorkDate,
            step
        )
        return generateWorkMonthUseCase.generate(previousMonthDate, actualDateFirstWork, step)
    }

    companion object {
        private const val VALUE = 1L
    }
}