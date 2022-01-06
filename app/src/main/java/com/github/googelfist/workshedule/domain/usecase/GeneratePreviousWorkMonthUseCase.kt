package com.github.googelfist.workshedule.domain.usecase

import com.github.googelfist.workshedule.domain.models.Month
import java.time.LocalDate

class GeneratePreviousWorkMonthUseCase(
    private val generateWorkMonthUseCase: GenerateWorkMonthUseCase,
    private val getActualDateFirstWorkUseCase: GetActualDateFirstWorkUseCase
) {

    fun generate(month: Month, firstWorkDate: LocalDate, step: Int): Month {
        val previousMonthDate = month.date.minusMonths(VALUE)
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