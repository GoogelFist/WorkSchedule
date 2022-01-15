package com.github.googelfist.workschedule.presentation

import androidx.lifecycle.ViewModel
import com.github.googelfist.workschedule.domain.models.MonthDTO
import com.github.googelfist.workschedule.domain.usecase.GenerateNextWorkMonthUseCase
import com.github.googelfist.workschedule.domain.usecase.GeneratePreviousWorkMonthUseCase
import com.github.googelfist.workschedule.domain.usecase.GenerateWorkMonthUseCase
import com.github.googelfist.workschedule.domain.usecase.GetDateNowUseCase
import java.time.LocalDate

class MainViewModel(
    getDateNowUseCase: GetDateNowUseCase,
    private val generateWorkMonthUseCase: GenerateWorkMonthUseCase,
    private val generateNextWorkMonthUseCase: GenerateNextWorkMonthUseCase,
    private val generatePreviousWorkMonthUseCase: GeneratePreviousWorkMonthUseCase
) : ViewModel() {
    private var date: LocalDate = getDateNowUseCase()

    lateinit var monthDTO: MonthDTO

    // TODO: 12-Jan-22 naming methods
    fun generateCurrentMonth() {
        monthDTO = generateWorkMonthUseCase(date)
    }

    fun generateNextMonth() {
        monthDTO = generateNextWorkMonthUseCase(monthDTO)
    }

    fun generatePreviousMonth() {
        monthDTO = generatePreviousWorkMonthUseCase(monthDTO)
    }
}
