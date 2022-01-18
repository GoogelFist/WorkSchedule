package com.github.googelfist.workschedule.data.schedulesgenerator.work.fabric

import com.github.googelfist.workschedule.data.schedulesgenerator.work.scheduletype.ScheduleType
import com.github.googelfist.workschedule.domain.models.days.ActiveDay
import com.github.googelfist.workschedule.domain.models.days.Day
import com.github.googelfist.workschedule.domain.models.days.InActiveDay
import com.github.googelfist.workschedule.domain.models.days.Today
import java.time.LocalDate

class DaysFabricImpl : DaysFabric {

    override fun getWorkDay(
        dateInMonth: LocalDate,
        activeDate: LocalDate,
        firstWorkDate: LocalDate,
        scheduleType: ScheduleType
    ): Day {
        val workSchedule = scheduleType.getWorkSchedule(firstWorkDate)

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
                year = dateInMonth.year,
                isWork = false,
                isWeekend = true
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
                    year = dateInMonth.year,
                    isWork = false,
                    isWeekend = true
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
                year = dateInMonth.year,
                isWork = false,
                isWeekend = true
            )
        }
    }

    override fun getDefaultDay(dateInMonth: LocalDate, activeDate: LocalDate): Day {

        return when {
            isInActiveDay(dateInMonth, activeDate) -> InActiveDay(
                value = dateInMonth.dayOfMonth,
                month = dateInMonth.monthValue,
                year = dateInMonth.year
            )

            isToday(dateInMonth) ->
                Today(
                    value = dateInMonth.dayOfMonth,
                    month = dateInMonth.monthValue,
                    year = dateInMonth.year
                )

            else -> ActiveDay(
                value = dateInMonth.dayOfMonth,
                month = dateInMonth.monthValue,
                year = dateInMonth.year
            )
        }
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
}
