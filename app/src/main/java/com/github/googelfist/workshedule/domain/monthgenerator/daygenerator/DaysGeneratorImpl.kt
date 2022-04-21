package com.github.googelfist.workshedule.domain.monthgenerator.daygenerator

import com.github.googelfist.workshedule.domain.Repository
import com.github.googelfist.workshedule.domain.models.day.Day
import com.github.googelfist.workshedule.domain.monthgenerator.fabric.DaysFabric
import java.time.LocalDate
import javax.inject.Inject

class DaysGeneratorImpl @Inject constructor(
    private val daysFabric: DaysFabric,
    private val repository: Repository
) : DaysGenerator {

    override suspend fun getDays(date: LocalDate): List<Day> {

        val scheduleType = repository.loadScheduleType()

        return when (scheduleType) {
            TWO_IN_TWO_SCHEDULE_TYPE -> generateWorkDays(date)
            else -> {
                generateDefaultDays(date)
            }
        }
    }

    private fun generateDefaultDays(date: LocalDate): List<Day> {
        var firstMondayInMonth = getDateOfFirstMonday(date)

        val dayList = mutableListOf<Day>()
        repeat(MAX_DAY_COUNT) {
            dayList.add(daysFabric.getDefaultDay(firstMondayInMonth, date))

            firstMondayInMonth = firstMondayInMonth.plusDays(ONE_VALUE)
        }
        return dayList
    }

    private suspend fun generateWorkDays(date: LocalDate): List<Day> {
        var firstMondayInMonth = getDateOfFirstMonday(date)

        val dayList = mutableListOf<Day>()
        repeat(MAX_DAY_COUNT) {
            dayList.add(daysFabric.getWorkDay(firstMondayInMonth, date))

            firstMondayInMonth = firstMondayInMonth.plusDays(ONE_VALUE)
        }
        return dayList
    }

    private fun getDateOfFirstMonday(date: LocalDate): LocalDate {
        val firstDayOfMonth = LocalDate.of(date.year, date.month, ONE_VALUE.toInt())
        val dayOfWeek = firstDayOfMonth.dayOfWeek.value - ONE_VALUE

        return firstDayOfMonth.minusDays(dayOfWeek)
    }

    companion object {
        private const val ONE_VALUE = 1L
        private const val MAX_DAY_COUNT = 42

        private const val TWO_IN_TWO_SCHEDULE_TYPE = "2/2"
        private const val DEFAULT_SCHEDULE_TYPE = "Default"
    }
}