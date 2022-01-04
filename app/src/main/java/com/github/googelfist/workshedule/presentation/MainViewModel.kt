package com.github.googelfist.workshedule.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.github.googelfist.workshedule.daysgenerator.DaysGeneratorImpl
import com.github.googelfist.workshedule.domain.models.Day
import com.github.googelfist.workshedule.domain.usecase.*
import java.time.LocalDate

class MainViewModel : ViewModel() {

    // TODO: 26-Dec-21 incorrect architecture
    private val daysGenerator = DaysGeneratorImpl()

    private val generateWorkDaysUseCase = GenerateWorkDaysUseCase(daysGenerator)
    private val getDateOfChosenMonthUseCase = GetDateNowUseCase()
    private val getDateNextMonthUseCase = GetDateNextMonthUseCase()
    private val getDatePreviousMonthUseCase = GetDatePreviousMonthUseCase()
    private val formatDateUseCase = FormatDateUseCase()

    lateinit var formattedDateLD: LiveData<String>
    lateinit var date: LocalDate
    lateinit var dayListLD: LiveData<List<Day>>

    fun generateCurrentMonth() {
        date = getDateOfChosenMonthUseCase.getDateNow()
        dayListLD = generateWorkDaysUseCase.generateWorkDays(date)
        formattedDateLD = formatDateUseCase.formatDate(date)
    }

    fun getNextMonth() {
        val dateNext = getDateNextMonthUseCase.getDateNextMonth(date)
        dayListLD = generateWorkDaysUseCase.generateWorkDays(dateNext)
        formattedDateLD = formatDateUseCase.formatDate(dateNext)
        date = dateNext
    }

    fun getPreviousMonth() {
        val datePrevious = getDatePreviousMonthUseCase.getDatePreviousMonth(date)
        dayListLD = generateWorkDaysUseCase.generateWorkDays(datePrevious)
        formattedDateLD = formatDateUseCase.formatDate(datePrevious)
        date = datePrevious
    }
}