package com.github.googelfist.workshedule.domain.usecase

import java.time.LocalDate

class GetDatePreviousMonthUseCase {
    fun getDatePreviousMonth(date: LocalDate): LocalDate {
        return date.minusMonths(ONE_VALUE)
    }

    companion object {
        private const val ONE_VALUE = 1L
    }
}