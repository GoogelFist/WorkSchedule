package com.github.googelfist.workshedule.daysgenerator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.googelfist.workshedule.domain.models.Day
import com.github.googelfist.workshedule.domain.DaysGenerator
import java.time.LocalDate

class DaysGeneratorImpl : DaysGenerator {

    private val dayListLD = MutableLiveData<List<Day>>()

    // TODO: 02-Jan-22 add step parameter
    override fun generateDays(date: LocalDate): LiveData<List<Day>> {
        var firstDay = getFirstDay(date)

        val dayList = mutableListOf<Day>()
        for (i in 0 until 42) {
            dayList.add(
                Day(
                    value = firstDay.dayOfMonth,
                    month = firstDay.monthValue,
                    year = firstDay.year,
                    isActive = false,
                    isToday = false,
                    isWork = false,
                    isWeekend = false
                )
            )
            firstDay = firstDay.plusDays(ONE_VALUE)
        }
        dayList.setActiveDays(date)
        dayList.setTodayDay()
        dayListLD.value = dayList
        return dayListLD
    }

    override fun generateDays(date: LocalDate, startDay: LocalDate): List<Day> {
        var firstDay = getFirstDay(date)

        val dayList = mutableListOf<Day>()
        for (i in 0 until 42) {
            dayList.add(
                Day(
                    value = firstDay.dayOfMonth,
                    month = firstDay.monthValue,
                    year = firstDay.year,
                    isActive = false,
                    isToday = false,
                    isWork = false,
                    isWeekend = false
                )
            )
            firstDay = firstDay.plusDays(ONE_VALUE)
        }
        dayList.setActiveDays(date)
        dayList.setTodayDay()

        dayList.setWorkDays(startDay)
        dayList.setWeekendDays(startDay)

        return dayList
    }

    private fun List<Day>.setActiveDays(date: LocalDate) {
        this.map { it.isActive = it.month == date.month.value }
    }

    private fun List<Day>.setTodayDay() {
        val today = LocalDate.now()
        this.map { it.isToday = it.month == today.monthValue && it.value == today.dayOfMonth }
    }

    private fun List<Day>.setWorkDays(date: LocalDate) {
        val workDays = getWorkDays(date)

        this.map { it.isWork = workDays.contains(LocalDate.of(it.year, it.month, it.value)) }
    }

    private fun List<Day>.setWeekendDays(date: LocalDate) {
        val weekendDays = getWeekendDays(date)

        this.map { it.isWeekend = weekendDays.contains(LocalDate.of(it.year, it.month, it.value)) }
    }

    private fun getWorkDays(date: LocalDate): Set<LocalDate> {
        var lowDate = date.minusDays(RANGE_HALF_LENGTH)
        val dateSet = mutableSetOf<LocalDate>()

        for (i in 0..RANGE_LENGTH step STEP.toInt()) {
            dateSet.add(lowDate)
            dateSet.add(lowDate.plusDays(ONE_VALUE))
            lowDate = lowDate.plusDays(STEP)
        }
        return dateSet
    }

    private fun getWeekendDays(date: LocalDate): Set<LocalDate> {
        var lowDate = date.minusDays(RANGE_HALF_LENGTH - TWO_VALUE)
        val dateSet = mutableSetOf<LocalDate>()

        for (i in 0..RANGE_LENGTH step STEP.toInt()) {
            dateSet.add(lowDate)
            dateSet.add(lowDate.plusDays(ONE_VALUE))
            lowDate = lowDate.plusDays(STEP)
        }
        return dateSet
    }

    private fun getFirstDay(date: LocalDate): LocalDate {
        val datesListOfRange = mutableListOf<LocalDate>()
        var dateMinRange = date.minusDays(RANGE_HALF_LENGTH)
        for (i in 0 until RANGE_LENGTH) {
            datesListOfRange.add(dateMinRange)
            dateMinRange = dateMinRange.plusDays(ONE_VALUE)
        }

        val currentMonth = date.month
        val firstMonday = datesListOfRange.filter { it.month == currentMonth }
            .find { it.dayOfWeek.value == MONDAY }
            ?: throw RuntimeException("First monday of active month not found")

        val firstDay = when (firstMonday.dayOfMonth) {
            ONE_VALUE.toInt() -> firstMonday
            else -> firstMonday.minusWeeks(ONE_VALUE)
        }
        return firstDay
    }

    companion object {
        private const val ONE_VALUE = 1L
        private const val TWO_VALUE = 2L
        private const val STEP = 4L
        private const val RANGE_LENGTH = 80
        private const val RANGE_HALF_LENGTH = RANGE_LENGTH / 2L
        private const val MONDAY = 1
    }
}