package com.github.googelfist.workschedule.data.schedulesgenerator.daysfabric

import com.github.googelfist.workschedule.domain.models.days.ActiveDay
import com.github.googelfist.workschedule.domain.models.days.Day
import com.github.googelfist.workschedule.domain.models.days.InActiveDay
import com.github.googelfist.workschedule.domain.models.days.Today
import java.time.LocalDate

class TwoInTwoScheduleDaysFabricImpl : DaysFabric {

    override fun getDay(
        dateInMonth: LocalDate,
        activeDate: LocalDate,
        firstWorkDate: LocalDate
    ): Day {
        val workSchedule = getWorkSchedule(firstWorkDate)

        return when {
            isWorkDay(workSchedule, dateInMonth) && isInActiveDay(
                dateInMonth,
                activeDate
            ) -> InActiveDay(
                value = dateInMonth.dayOfMonth,
                month = dateInMonth.monthValue,
                year = dateInMonth.year,
                isWork = true,
                isWeekend = false
            )

            isInActiveDay(dateInMonth, activeDate) -> InActiveDay(
                value = dateInMonth.dayOfMonth,
                month = dateInMonth.monthValue,
                year = dateInMonth.year
            )

            isWorkDay(workSchedule, dateInMonth) && isToday(dateInMonth) ->
                Today(
                    value = dateInMonth.dayOfMonth,
                    month = dateInMonth.monthValue,
                    year = dateInMonth.year,
                    isWork = true,
                    isWeekend = false
                )

            isToday(dateInMonth) ->
                Today(
                    value = dateInMonth.dayOfMonth,
                    month = dateInMonth.monthValue,
                    year = dateInMonth.year
                )

            isWorkDay(workSchedule, dateInMonth) && isActiveDay(dateInMonth, activeDate) ->
                ActiveDay(
                    value = dateInMonth.dayOfMonth,
                    month = dateInMonth.monthValue,
                    year = dateInMonth.year,
                    isWork = true,
                    isWeekend = false
                )

            else -> ActiveDay(
                value = dateInMonth.dayOfMonth,
                month = dateInMonth.monthValue,
                year = dateInMonth.year
            )
        }
    }

    private fun getWorkSchedule(date: LocalDate): Set<LocalDate> {
        var lowDate = date.minusDays(RANGE_HALF_LENGTH)
        val dateSet = mutableSetOf<LocalDate>()

        (ZERO..RANGE_LENGTH step STEP).forEach { _ ->
            dateSet.add(lowDate)
            dateSet.add(lowDate.plusDays(ONE_VALUE))
            lowDate = lowDate.plusDays(STEP.toLong())
        }
        return dateSet
    }

    private fun isActiveDay(dateInMonth: LocalDate, date: LocalDate): Boolean {
        return dateInMonth.monthValue == date.monthValue
    }

    private fun isInActiveDay(dateInMonth: LocalDate, date: LocalDate): Boolean {
        return dateInMonth.monthValue != date.monthValue
    }

    private fun isToday(dateInMonth: LocalDate): Boolean {
        val today = LocalDate.now()
        return dateInMonth == today
    }

    private fun isWorkDay(workDates: Set<LocalDate>, dateInMonth: LocalDate): Boolean {
        return workDates.contains(dateInMonth)
    }

    companion object {
        private const val STEP = 4
        private const val ONE_VALUE = 1L
        private const val RANGE_LENGTH = 80
        private const val RANGE_HALF_LENGTH = RANGE_LENGTH / 2L
        private const val ZERO = 0
    }
}
