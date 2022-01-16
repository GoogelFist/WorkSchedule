package com.github.googelfist.workschedule.presentation

import androidx.lifecycle.ViewModel
import com.github.googelfist.workschedule.domain.models.MonthDTO
import com.github.googelfist.workschedule.domain.usecase.GenerateMonthUseCase
import com.github.googelfist.workschedule.domain.usecase.GenerateNextMonthUseCase
import com.github.googelfist.workschedule.domain.usecase.GeneratePreviousMonthUseCase
import com.github.googelfist.workschedule.domain.usecase.GetDateNowUseCase
import java.time.LocalDate

class MainViewModel(
    getDateNowUseCase: GetDateNowUseCase,
    private val generateMonthUseCase: GenerateMonthUseCase,
    private val generateNextMonthUseCase: GenerateNextMonthUseCase,
    private val generatePreviousMonthUseCase: GeneratePreviousMonthUseCase
) : ViewModel() {
    private var date: LocalDate = getDateNowUseCase()

    lateinit var monthDTO: MonthDTO

    // TODO: 12-Jan-22 naming methods
    fun generateCurrentMonth() {
        monthDTO = generateMonthUseCase.generate(date)
    }

    fun generateNextMonth() {
        monthDTO = generateNextMonthUseCase(monthDTO)
    }

    fun generatePreviousMonth() {
        monthDTO = generatePreviousMonthUseCase(monthDTO)
    }
}
