package com.github.googelfist.workshedule.domain.monthgenerator.def.daysgenerator

import com.github.googelfist.workshedule.domain.models.day.Day
import com.github.googelfist.workshedule.domain.monthgenerator.def.fabric.DefaultDaysFabric
import java.time.LocalDate
import javax.inject.Inject

class DefaultDaysGeneratorImpl @Inject constructor(private val defaultDaysFabric: DefaultDaysFabric) :
    DefaultDaysGenerator {

    override fun generateDays(date: LocalDate): List<Day> {
        var firstDay = getDateOfFirstMonday(date)

        val dayList = mutableListOf<Day>()
        repeat(MAX_DAY_COUNT) {
            dayList.add(defaultDaysFabric.getDefaultDay(firstDay, date))

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