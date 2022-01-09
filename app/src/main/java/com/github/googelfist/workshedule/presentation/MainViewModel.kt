package com.github.googelfist.workshedule.presentation

import androidx.lifecycle.ViewModel
import com.github.googelfist.workshedule.daysgenerator.DaysGeneratorImpl
import com.github.googelfist.workshedule.daysgenerator.SchedulesGeneratorImpl
import com.github.googelfist.workshedule.domain.models.Month
import com.github.googelfist.workshedule.domain.usecase.*
import java.time.LocalDate

class MainViewModel : ViewModel() {

    // TODO: 26-Dec-21 incorrect architecture
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


    private var date: LocalDate = getDateOfChosenMonthUseCase.getDateNow()

    // TODO: 06-Jan-22 this will be in base
    private var firstWorkDate: LocalDate = LocalDate.of(2021, 12, 1)

    lateinit var month: Month

    fun generateCurrentMonth() {
//        month = generateMonthUseCase.generate(date)
        firstWorkDate =
            getActualDateFirstWorkUseCase.getActualDateFirstWork(date, firstWorkDate, STEP)
        month = generateWorkMonthUseCase.generate(date, firstWorkDate, STEP)
    }

    fun generateNextMonth() {
//        month = generateNextMonthUseCase.generate(month)
        month = generateNextWorkMonthUseCase.generate(month, firstWorkDate, STEP)
    }

    fun generatePreviousMonth() {
//        month = generatePreviousMonthUseCase.generate(month)
        month = generatePreviousWorkMonthUseCase.generate(month, firstWorkDate, STEP)
    }

    companion object {
        private const val STEP = 4
    }
}