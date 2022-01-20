package com.github.googelfist.workschedule.presentation

import androidx.lifecycle.ViewModel
import com.github.googelfist.workschedule.domain.usecase.FormatDateUseCase
import com.github.googelfist.workschedule.domain.usecase.GenerateMonthUseCase

class MainViewModel(
    private val generateCurrentMonthUseCase: GenerateMonthUseCase,
    private val generateNextMonthUseCase: GenerateMonthUseCase,
    private val generatePreviousMonthUseCase: GenerateMonthUseCase,
    formatDateUseCase: FormatDateUseCase
) : ViewModel() {

    var dayListLD = generateCurrentMonthUseCase()
    var formatDateLD = formatDateUseCase()

    fun onGenerateCurrentMonth() {
        generateCurrentMonthUseCase()
    }

    fun onGenerateNextMonth() {
        generateNextMonthUseCase()
    }

    fun onGeneratePreviousMonth() {
        generatePreviousMonthUseCase()
    }
}
