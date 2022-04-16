package com.github.googelfist.workshedule.domain.monthgenerator.twointwo.daygenerator

import com.github.googelfist.workshedule.domain.models.day.Day
import com.github.googelfist.workshedule.domain.monthgenerator.twointwo.fabric.WorkTwoInTwoDaysFabric
import java.time.LocalDate
import javax.inject.Inject

class WorkTwoInTwoDaysGeneratorImpl @Inject constructor(private val daysFabric: WorkTwoInTwoDaysFabric) :
    WorkTwoInTwoDaysGenerator {

    override fun generateDays(date: LocalDate): List<Day> {
        var firstMondayInMonth = getDateOfFirstMonday(date)

        val firstWorkDate = getFirstWorkDay()
        val actualFirstWorkDate = getActualFirstWorkDay(date, firstWorkDate)
        val workSchedule = getWorkSchedule(actualFirstWorkDate)

        val dayList = mutableListOf<Day>()
        repeat(MAX_DAY_COUNT) {
            dayList.add(daysFabric.getWorkDay(firstMondayInMonth, date, workSchedule))

            firstMondayInMonth = firstMondayInMonth.plusDays(ONE_VALUE)
        }
        return dayList.toList()
    }

    private fun getDateOfFirstMonday(date: LocalDate): LocalDate {
        val firstDayOfMonth = LocalDate.of(date.year, date.month, ONE_VALUE.toInt())
        val dayOfWeek = firstDayOfMonth.dayOfWeek.value - ONE_VALUE

        return firstDayOfMonth.minusDays(dayOfWeek)
    }

    // TODO:  hardcoded value
    private fun getFirstWorkDay(): LocalDate {
        return LocalDate.of(2022, 4, 16)
//        return LocalDate.of(2022, 4, 17)
    }

    private fun getActualFirstWorkDay(activeDate: LocalDate, firstWorkDate: LocalDate): LocalDate {
        var curDate = firstWorkDate
        when {
            curDate == activeDate -> return activeDate
            curDate < activeDate -> while (curDate < activeDate) {
                curDate = curDate.plusDays(STEP)
            }
            curDate > activeDate -> while (curDate > activeDate) {
                curDate = curDate.minusDays(STEP)
            }
        }
        return curDate
    }

    private fun getWorkSchedule(date: LocalDate): Set<LocalDate> {
        var lowDate = date.minusDays(RANGE_HALF_LENGTH)
        val dateSet = mutableSetOf<LocalDate>()

        (ZERO..RANGE_LENGTH step STEP.toInt()).forEach { _ ->
            dateSet.add(lowDate)
            dateSet.add(lowDate.plusDays(ONE_VALUE))
            lowDate = lowDate.plusDays(STEP)
        }
        return dateSet
    }

    companion object {
        private const val ONE_VALUE = 1L
        private const val MAX_DAY_COUNT = 42

        private const val STEP = 4L
        private const val RANGE_LENGTH = 80
        private const val RANGE_HALF_LENGTH = RANGE_LENGTH / 2L
        private const val ZERO = 0
    }
}