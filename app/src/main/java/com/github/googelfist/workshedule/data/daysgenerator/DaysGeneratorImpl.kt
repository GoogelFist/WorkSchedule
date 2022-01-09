package com.github.googelfist.workshedule.data.daysgenerator

import com.github.googelfist.workshedule.domain.models.ActiveDay
import com.github.googelfist.workshedule.domain.models.Day
import com.github.googelfist.workshedule.domain.models.InActiveDay
import java.time.LocalDate

class DaysGeneratorImpl : DaysGenerator {

    override fun generateDays(date: LocalDate): List<Day> {
        var firstDay = getFirstDay(date)

        val dayList = mutableListOf<Day>()
        for (i in 0 until 42) {
            dayList.add(getDay(firstDay, date))

            firstDay = firstDay.plusDays(ONE_VALUE)
        }
        return dayList
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

    private fun getDay(firstDate: LocalDate, date: LocalDate): Day {
        val today = LocalDate.now()
        return when {
            firstDate.monthValue != date.monthValue -> InActiveDay(
                value = firstDate.dayOfMonth,
                month = firstDate.monthValue,
                year = firstDate.year
            )

            firstDate.monthValue == today.monthValue && firstDate.dayOfMonth == today.dayOfMonth ->
                ActiveDay(
                    value = firstDate.dayOfMonth,
                    month = firstDate.monthValue,
                    year = firstDate.year,
                    isToday = true
                )

            else -> ActiveDay(
                value = firstDate.dayOfMonth,
                month = firstDate.monthValue,
                year = firstDate.year
            )
        }
    }

    companion object {
        private const val ONE_VALUE = 1L
        private const val RANGE_LENGTH = 80
        private const val RANGE_HALF_LENGTH = RANGE_LENGTH / 2L
        private const val MONDAY = 1
    }
}