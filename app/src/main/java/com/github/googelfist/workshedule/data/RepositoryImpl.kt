package com.github.googelfist.workshedule.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.googelfist.workshedule.domain.Repository
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object RepositoryImpl : Repository {
    private const val ONE_VALUE = 1L
    private const val FORMAT_PATTERN = "MMM yyyy"

    private val dayListLD = MutableLiveData<List<Int>>()
    private val dateOfChosenMonthLD = MutableLiveData<String>()

    private var date: LocalDate = LocalDate.now()

    override fun generateCurrentMonth(): LiveData<List<Int>> {
        date = LocalDate.now()
        val dayList = generateMonth(date)
        dayListLD.value = dayList
        return dayListLD
    }

    override fun generateNextMonth(): LiveData<List<Int>> {
        date = date.plusMonths(ONE_VALUE)
        val nextMonthDayList = generateMonth(date)
        dayListLD.value = nextMonthDayList
        return dayListLD
    }

    override fun generatePreviousMonth(): LiveData<List<Int>> {
        date = date.minusMonths(ONE_VALUE)
        val previousMonthDayList = generateMonth(date)
        dayListLD.value = previousMonthDayList
        return dayListLD
    }

    override fun getDateOfChosenMonth(): LiveData<String> {
        val currentDate = DateTimeFormatter.ofPattern(FORMAT_PATTERN).format(date)
        dateOfChosenMonthLD.value = currentDate
        return dateOfChosenMonthLD
    }

    private fun generateMonth(date: LocalDate): List<Int> {
        val firstDayOfMonth = date.minusDays(date.dayOfMonth - ONE_VALUE)
        var startDaysPreviousMonth =
            firstDayOfMonth.minusDays(firstDayOfMonth.dayOfWeek.value - ONE_VALUE)

        val dayList = mutableListOf<Int>()
        for (i in 0 until 42) {
            dayList.add(startDaysPreviousMonth.dayOfMonth)
            startDaysPreviousMonth = startDaysPreviousMonth.plusDays(ONE_VALUE)
        }
        return dayList
    }
}