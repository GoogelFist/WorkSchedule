package com.github.googelfist.workshedule.presentation

import androidx.lifecycle.ViewModel
import com.github.googelfist.workshedule.daysgenerator.DaysGeneratorImpl
import com.github.googelfist.workshedule.domain.models.Month
import com.github.googelfist.workshedule.domain.usecase.*
import java.time.LocalDate

class MainViewModel : ViewModel() {

    // TODO: 26-Dec-21 incorrect architecture
    private val daysGenerator = DaysGeneratorImpl()

    private val getDateOfChosenMonthUseCase = GetDateNowUseCase()
    private val formatDateUseCase = FormatDateUseCase()

    private val generateMonthUseCase = GenerateMonthUseCase(daysGenerator, formatDateUseCase)
    private val generateNextMonthUseCase = GenerateNextMonthUseCase(generateMonthUseCase)
    private val generatePreviousMonthUseCase = GeneratePreviousMonthUseCase(generateMonthUseCase)

    private var date: LocalDate = getDateOfChosenMonthUseCase.getDateNow()

    lateinit var month: Month

    fun generateCurrentMonth() {
        month = generateMonthUseCase.generate(date)
    }

    fun generateNextMonth() {
        month = generateNextMonthUseCase.generate(month)
    }

    fun generatePreviousMonth() {
        month = generatePreviousMonthUseCase.generate(month)
    }
}