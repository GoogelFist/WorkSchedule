package com.github.googelfist.workshedule.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.googelfist.workshedule.domain.schedulesgenerator.SchedulesGeneratorImpl
import com.github.googelfist.workshedule.domain.schedulesgenerator.daysgenerator.DaysGeneratorImpl
import com.github.googelfist.workshedule.domain.usecase.*

class MainViewModelFactory : ViewModelProvider.Factory {
    private val daysGenerator = DaysGeneratorImpl()
    private val schedulesGenerator = SchedulesGeneratorImpl(daysGenerator)

    private val getDateOfChosenMonthUseCase = GetDateNowUseCase()
    private val formatDateUseCase = FormatDateUseCase()

    private val generateMonthUseCase = GenerateMonthUseCase(schedulesGenerator, formatDateUseCase)
    private val generateNextMonthUseCase = GenerateNextMonthUseCase(generateMonthUseCase)
    private val generatePreviousMonthUseCase = GeneratePreviousMonthUseCase(generateMonthUseCase)

    private val getActualDateFirstWorkUseCase = GetActualDateFirstWorkUseCase()

    private val generateWorkMonthUseCase =
        GenerateWorkMonthUseCase(schedulesGenerator, formatDateUseCase)

    private val generateNextWorkMonthUseCase =
        GenerateNextWorkMonthUseCase(generateWorkMonthUseCase, getActualDateFirstWorkUseCase)

    private val generatePreviousWorkMonthUseCase =
        GeneratePreviousWorkMonthUseCase(generateWorkMonthUseCase, getActualDateFirstWorkUseCase)


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(
            generateMonthUseCase,
            getDateOfChosenMonthUseCase,
            getActualDateFirstWorkUseCase,
            generateNextMonthUseCase,
            generatePreviousMonthUseCase,
            generateWorkMonthUseCase,
            generateNextWorkMonthUseCase,
            generatePreviousWorkMonthUseCase
        ) as T
    }
}