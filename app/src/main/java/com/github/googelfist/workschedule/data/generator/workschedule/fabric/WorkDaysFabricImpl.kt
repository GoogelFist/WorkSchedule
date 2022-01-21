package com.github.googelfist.workschedule.data.generator.workschedule.fabric

import com.github.googelfist.workschedule.data.generator.scheduletype.ScheduleTyper
import com.github.googelfist.workschedule.domain.models.days.Day
import com.github.googelfist.workschedule.domain.models.days.workday.WorkActiveDay
import com.github.googelfist.workschedule.domain.models.days.workday.WorkInActiveDay
import com.github.googelfist.workschedule.domain.models.days.workday.WorkToday
import java.time.LocalDate

class WorkDaysFabricImpl : WorkDaysFabric {

    override fun getWorkDay(
        dateInMonth: LocalDate,
        activeDate: LocalDate,
        firstWorkDate: LocalDate,
        scheduleTyper: ScheduleTyper
    ): Day {
        val workSchedule = scheduleTyper.getWorkSchedule(firstWorkDate)

        return when {
            isWorkDay(workSchedule, dateInMonth) && isInActiveDay(
                dateInMonth,
                activeDate
            ) -> WorkInActiveDay(
                value = dateInMonth.dayOfMonth,
                month = dateInMonth.monthValue,
                year = dateInMonth.year,
                isWork = true,
                isWeekend = false
            )

            isInActiveDay(dateInMonth, activeDate) -> WorkInActiveDay(
                value = dateInMonth.dayOfMonth,
                month = dateInMonth.monthValue,
                year = dateInMonth.year,
                isWork = false,
                isWeekend = true
            )

            isWorkDay(workSchedule, dateInMonth) && isToday(dateInMonth) ->
                WorkToday(
                    value = dateInMonth.dayOfMonth,
                    month = dateInMonth.monthValue,
                    year = dateInMonth.year,
                    isWork = true,
                    isWeekend = false
                )

            isToday(dateInMonth) ->
                WorkToday(
                    value = dateInMonth.dayOfMonth,
                    month = dateInMonth.monthValue,
                    year = dateInMonth.year,
                    isWork = false,
                    isWeekend = true
                )

            isWorkDay(workSchedule, dateInMonth) && isActiveDay(dateInMonth, activeDate) ->
                WorkActiveDay(
                    value = dateInMonth.dayOfMonth,
                    month = dateInMonth.monthValue,
                    year = dateInMonth.year,
                    isWork = true,
                    isWeekend = false
                )

            else -> WorkActiveDay(
                value = dateInMonth.dayOfMonth,
                month = dateInMonth.monthValue,
                year = dateInMonth.year,
                isWork = false,
                isWeekend = true
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
