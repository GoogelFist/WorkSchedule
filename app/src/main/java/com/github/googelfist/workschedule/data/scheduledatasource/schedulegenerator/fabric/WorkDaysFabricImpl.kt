package com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.fabric

import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.datecontainer.DateContainer
import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.models.Day
import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.models.workday.WorkActiveDay
import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.models.workday.WorkInActiveDay
import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.models.workday.WorkToday
import java.time.LocalDate
import javax.inject.Inject

class WorkDaysFabricImpl @Inject constructor(private val dateContainer: DateContainer) :
    WorkDaysFabric {

    override fun getWorkDay(
        dateInMonth: LocalDate,
        activeDate: LocalDate,
        firstWorkDay: LocalDate,
        actualFirstWorkDay: LocalDate,
        workSchedule: Set<LocalDate>
    ): Day {

        return when {
            isWorkDay(workSchedule, dateInMonth) && isInActiveDay(
                dateInMonth,
                activeDate
            ) -> WorkInActiveDay(
                day = dateInMonth.dayOfMonth,
                month = dateInMonth.monthValue,
                year = dateInMonth.year,
                isWork = true,
                isWeekend = false
            )

            isInActiveDay(dateInMonth, activeDate) -> WorkInActiveDay(
                day = dateInMonth.dayOfMonth,
                month = dateInMonth.monthValue,
                year = dateInMonth.year,
                isWork = false,
                isWeekend = true
            )

            isWorkDay(workSchedule, dateInMonth) && isToday(dateInMonth) ->
                WorkToday(
                    day = dateInMonth.dayOfMonth,
                    month = dateInMonth.monthValue,
                    year = dateInMonth.year,
                    isWork = true,
                    isWeekend = false
                )

            isToday(dateInMonth) ->
                WorkToday(
                    day = dateInMonth.dayOfMonth,
                    month = dateInMonth.monthValue,
                    year = dateInMonth.year,
                    isWork = false,
                    isWeekend = true
                )

            isWorkDay(workSchedule, dateInMonth) && isActiveDay(dateInMonth, activeDate) ->
                WorkActiveDay(
                    day = dateInMonth.dayOfMonth,
                    month = dateInMonth.monthValue,
                    year = dateInMonth.year,
                    isWork = true,
                    isWeekend = false
                )

            else -> WorkActiveDay(
                day = dateInMonth.dayOfMonth,
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
        val today = dateContainer.getDateNow()
        return dateInMonth == today
    }

    private fun isWorkDay(workDates: Set<LocalDate>, dateInMonth: LocalDate): Boolean {
        return workDates.contains(dateInMonth)
    }
}
