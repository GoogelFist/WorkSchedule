package com.github.googelfist.workshedule.domain.usecase

import com.github.googelfist.workshedule.domain.models.Month
import java.time.LocalDate

class GenerateNextWorkMonthUseCase(
    private val generateWorkMonthUseCase: GenerateWorkMonthUseCase,
    private val getActualDateFirstWorkUseCase: GetActualDateFirstWorkUseCase
) {

    fun generate(month: Month, firstWorkDate: LocalDate, step: Int): Month {
        val nextMonthDate = month.date.plusMonths(VALUE)
        val actualDateFirstWork =
            getActualDateFirstWorkUseCase.getActualDateFirstWork(nextMonthDate, firstWorkDate, step)
        return generateWorkMonthUseCase.generate(nextMonthDate, actualDateFirstWork, step)
    }

    companion object {
        private const val VALUE = 1L
    }
}