package com.github.googelfist.workschedule.presentation

import androidx.lifecycle.ViewModel
import com.github.googelfist.workschedule.domain.usecase.FormatDateUseCase
import com.github.googelfist.workschedule.domain.usecase.GenerateCurrentMonthUseCase
import com.github.googelfist.workschedule.domain.usecase.GenerateNextMonthUseCase
import com.github.googelfist.workschedule.domain.usecase.GeneratePreviousMonthUseCase

class MainViewModel(
    private val generateCurrentMonthUseCase: GenerateCurrentMonthUseCase,
    private val generateNextMonthUseCase: GenerateNextMonthUseCase,
    private val generatePreviousMonthUseCase: GeneratePreviousMonthUseCase,
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
