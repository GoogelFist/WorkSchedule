package com.github.googelfist.workshedule.presentation

import androidx.lifecycle.ViewModel
import com.github.googelfist.workshedule.data.RepositoryImpl
import com.github.googelfist.workshedule.domain.*

class MainViewModel : ViewModel() {

    // TODO: 26-Dec-21 incorrect architecture
    private val repository = RepositoryImpl()

    private val generateCurrentMonthUseCase = GenerateCurrentMonthUseCase(repository)
    private val generateNextMonthUseCase = GenerateNextMonthUseCase(repository)
    private val generatePreviousMonthUseCase = GeneratePreviousMonthUseCase(repository)
    private val getDateOfChosenMonthUseCase = GetDateOfChosenMonthUseCase(repository)

    var dayList = generateCurrentMonthUseCase.generateCurrentMonth()
    var date = getDateOfChosenMonthUseCase.getDateOfChosenMonth()

    fun getNextMonth() {
        dayList = generateNextMonthUseCase.generateNextMonth()
        date = getDateOfChosenMonthUseCase.getDateOfChosenMonth()
    }

    fun getPreviousMonth() {
        dayList = generatePreviousMonthUseCase.generatePreviousMonth()
        date = getDateOfChosenMonthUseCase.getDateOfChosenMonth()
    }
}