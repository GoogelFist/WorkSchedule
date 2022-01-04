package com.github.googelfist.workshedule.domain.usecase

import java.time.LocalDate

class GetDateNextMonthUseCase {

    fun getDateNextMonth(date: LocalDate): LocalDate {
        return date.plusMonths(ONE_VALUE)
    }

    companion object {
        private const val ONE_VALUE = 1L
    }
}