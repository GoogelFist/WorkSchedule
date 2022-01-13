package com.github.googelfist.workshedule.presentation

import androidx.lifecycle.ViewModel
import com.github.googelfist.workshedule.domain.models.MonthDTO
import com.github.googelfist.workshedule.domain.usecase.GenerateMonthUseCase
import com.github.googelfist.workshedule.domain.usecase.GenerateNextMonthUseCase
import com.github.googelfist.workshedule.domain.usecase.GenerateNextWorkMonthUseCase
import com.github.googelfist.workshedule.domain.usecase.GeneratePreviousMonthUseCase
import com.github.googelfist.workshedule.domain.usecase.GeneratePreviousWorkMonthUseCase
import com.github.googelfist.workshedule.domain.usecase.GenerateWorkMonthUseCase
import com.github.googelfist.workshedule.domain.usecase.GetActualDateFirstWorkUseCase
import com.github.googelfist.workshedule.domain.usecase.GetDateNowUseCase
import java.time.LocalDate

// TODO: 13-Jan-22  too many parameters
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
    private var date: LocalDate = getDateOfChosenMonthUseCase()

    // TODO: 06-Jan-22 this will be in base
    private var firstWorkDate: LocalDate = LocalDate.of(2021, 12, 1)

    lateinit var monthDTO: MonthDTO

    // TODO: 12-Jan-22 naming methods
    fun generateCurrentMonth() {
//        month = generateMonthUseCase.generate(date)
        firstWorkDate =
            getActualDateFirstWorkUseCase(date, firstWorkDate, STEP)
        monthDTO = generateWorkMonthUseCase(date, firstWorkDate, STEP)
    }

    fun generateNextMonth() {
//        month = generateNextMonthUseCase.generate(month)
        monthDTO = generateNextWorkMonthUseCase(monthDTO, firstWorkDate, STEP)
    }

    fun generatePreviousMonth() {
//        month = generatePreviousMonthUseCase.generate(month)
        monthDTO = generatePreviousWorkMonthUseCase(monthDTO, firstWorkDate, STEP)
    }

    companion object {
        private const val STEP = 4
    }
}
