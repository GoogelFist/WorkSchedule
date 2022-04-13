package com.github.googelfist.workschedule.domain.schedulegenerator.daysgenerator

import com.github.googelfist.workschedule.domain.schedulegenerator.daysgenerator.fabric.DaysFabric
import com.github.googelfist.workschedule.domain.schedulegenerator.models.Day
import java.time.LocalDate
import javax.inject.Inject

class DaysGeneratorImpl @Inject constructor(private val daysFabric: DaysFabric) : DaysGenerator {

    override fun generateMonthDays(activeDate: LocalDate): List<Day> {
        var firstDay = getDateOfFirstMonday(activeDate)

        val dayList = mutableListOf<Day>()
        repeat(MAX_DAY_COUNT) {
            dayList.add(daysFabric.getDay(firstDay, activeDate))

            firstDay = firstDay.plusDays(ONE_VALUE)
        }
        return dayList.toList()
    }

    private fun getDateOfFirstMonday(date: LocalDate): LocalDate {
        val firstDayOfMonth = LocalDate.of(date.year, date.month, ONE_VALUE.toInt())
        val dayOfWeek = firstDayOfMonth.dayOfWeek.value - ONE_VALUE

        return firstDayOfMonth.minusDays(dayOfWeek)
    }

    companion object {
        private const val ONE_VALUE = 1L
        private const val MAX_DAY_COUNT = 42
    }
}
