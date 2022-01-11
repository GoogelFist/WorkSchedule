package com.github.googelfist.workshedule.presentation

import androidx.lifecycle.ViewModel
import com.github.googelfist.workshedule.domain.models.MonthDTO
import com.github.googelfist.workshedule.domain.usecase.*
import java.time.LocalDate

class MainViewModel(
    private val generateMonthUseCase: GenerateMonthUseCase,
    private val getDateOfChosenMonthUseCase: GetDateNowUseCase,
    private val getActualDateFirstWorkUseCase: GetActualDateFirstWorkUseCase,
    private val generateNextMonthUseCase: GenerateNextMonthUseCase,
    private val generatePreviousMonthUseCase: GeneratePreviousMonthUseCase,
    private val generateWorkMonthUseCase: GenerateWorkMonthUseCase,
    private val generateNextWorkMonthUseCase: GenerateNextWorkMonthUseCase,
    private val generatePreviousWorkMonthUseCase: GeneratePreviousWorkMonthUseCase
) : ViewModel() {
    private var date: LocalDate = getDateOfChosenMonthUseCase.getDateNow()

    // TODO: 06-Jan-22 this will be in base
    private var firstWorkDate: LocalDate = LocalDate.of(2021, 12, 1)

    lateinit var monthDTO: MonthDTO

    fun generateCurrentMonth() {
//        month = generateMonthUseCase.generate(date)
        firstWorkDate =
            getActualDateFirstWorkUseCase.getActualDateFirstWork(date, firstWorkDate, STEP)
        monthDTO = generateWorkMonthUseCase.generate(date, firstWorkDate, STEP)
    }

    fun generateNextMonth() {
//        month = generateNextMonthUseCase.generate(month)
        monthDTO = generateNextWorkMonthUseCase.generate(monthDTO, firstWorkDate, STEP)
    }

    fun generatePreviousMonth() {
//        month = generatePreviousMonthUseCase.generate(month)
        monthDTO = generatePreviousWorkMonthUseCase.generate(monthDTO, firstWorkDate, STEP)
    }

    companion object {
        private const val STEP = 4
    }
}
