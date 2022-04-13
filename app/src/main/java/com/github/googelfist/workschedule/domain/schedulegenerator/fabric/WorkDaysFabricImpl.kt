package com.github.googelfist.workschedule.domain.schedulegenerator.fabric

import com.github.googelfist.workschedule.domain.schedulegenerator.datecontainer.DateContainer
import com.github.googelfist.workschedule.domain.schedulegenerator.models.Day
import com.github.googelfist.workschedule.domain.schedulegenerator.models.WeekendDay
import com.github.googelfist.workschedule.domain.schedulegenerator.models.WorkDay
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
            isWorkShiftDay(workSchedule, dateInMonth) && !isCurrentMonthDay(
                dateInMonth,
                activeDate
            ) -> WorkDay(
                day = dateInMonth.dayOfMonth,
                month = dateInMonth.monthValue,
                year = dateInMonth.year,
                today = false,
                currentMonth = false
            )

            !isWorkShiftDay(workSchedule, dateInMonth) && !isCurrentMonthDay(
                dateInMonth,
                activeDate
            ) -> WeekendDay(
                day = dateInMonth.dayOfMonth,
                month = dateInMonth.monthValue,
                year = dateInMonth.year,
                today = false,
                currentMonth = false
            )

            isWorkShiftDay(workSchedule, dateInMonth) && isToday(dateInMonth) ->
                WorkDay(
                    day = dateInMonth.dayOfMonth,
                    month = dateInMonth.monthValue,
                    year = dateInMonth.year,
                    today = true,
                    currentMonth = true
                )

            !isWorkShiftDay(workSchedule, dateInMonth) && isToday(dateInMonth) ->
                WeekendDay(
                    day = dateInMonth.dayOfMonth,
                    month = dateInMonth.monthValue,
                    year = dateInMonth.year,
                    today = true,
                    currentMonth = true
                )

            isWorkShiftDay(workSchedule, dateInMonth) && isCurrentMonthDay(
                dateInMonth,
                activeDate
            ) ->
                WorkDay(
                    day = dateInMonth.dayOfMonth,
                    month = dateInMonth.monthValue,
                    year = dateInMonth.year,
                    today = false,
                    currentMonth = true
                )

            else -> WeekendDay(
                day = dateInMonth.dayOfMonth,
                month = dateInMonth.monthValue,
                year = dateInMonth.year,
                today = false,
                currentMonth = true
            )
        }
    }

    private fun isCurrentMonthDay(dateInMonth: LocalDate, date: LocalDate): Boolean {
        return dateInMonth.monthValue == date.monthValue
    }

    private fun isToday(dateInMonth: LocalDate): Boolean {
        val today = dateContainer.getDateNow()
        return dateInMonth == today
    }

    private fun isWorkShiftDay(workDates: Set<LocalDate>, dateInMonth: LocalDate): Boolean {
        return workDates.contains(dateInMonth)
    }
}
