package com.github.googelfist.workshedule.data.schedulesgenerator.daysgenerator

import com.github.googelfist.workshedule.data.schedulesgenerator.daysfabric.DaysFabric
import com.github.googelfist.workshedule.domain.models.days.Day
import java.time.LocalDate

class DaysGeneratorImpl(private val daysFabric: DaysFabric) : DaysGenerator {

    override fun generateDays(date: LocalDate): List<Day> {
        var firstDay = getFirstDate(date)

        val dayList = mutableListOf<Day>()
        repeat(MAX_DAY_COUNT) {
            dayList.add(daysFabric.getDay(firstDay, date))

            firstDay = firstDay.plusDays(ONE_VALUE)
        }
        return dayList
    }

    private fun getFirstDate(date: LocalDate): LocalDate {
        val datesListOfRange = mutableListOf<LocalDate>()
        var dateMinRange = date.minusDays(RANGE_HALF_LENGTH)
        repeat(RANGE_LENGTH) {
            datesListOfRange.add(dateMinRange)
            dateMinRange = dateMinRange.plusDays(ONE_VALUE)
        }

        val currentMonth = date.month
        val firstMonday = datesListOfRange.filter { it.month == currentMonth }
            .find { it.dayOfWeek.value == MONDAY }
            ?: throw NoSuchElementException("First monday of active month not found")

        val firstDay = when (firstMonday.dayOfMonth) {
            ONE_VALUE.toInt() -> firstMonday
            else -> firstMonday.minusWeeks(ONE_VALUE)
        }
        return firstDay
    }

    companion object {
        private const val ONE_VALUE = 1L
        private const val RANGE_LENGTH = 80
        private const val RANGE_HALF_LENGTH = RANGE_LENGTH / 2L
        private const val MONDAY = 1
        private const val MAX_DAY_COUNT = 42
    }
}