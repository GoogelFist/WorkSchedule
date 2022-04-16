package com.github.googelfist.workshedule.domain.monthgenerator.twointwo.fabric

import com.github.googelfist.workshedule.domain.models.day.Day
import com.github.googelfist.workshedule.domain.models.day.WeekendDay
import com.github.googelfist.workshedule.domain.models.day.WorkDay
import com.github.googelfist.workshedule.domain.monthgenerator.DateNowContainer
import java.time.LocalDate
import javax.inject.Inject

class WorkTwoInTwoDaysFabricImpl @Inject constructor(private val dateNowContainer: DateNowContainer) :
    WorkTwoInTwoDaysFabric {

    override fun getWorkDay(
        dateOfMonth: LocalDate,
        activeDate: LocalDate,
        workSchedule: Set<LocalDate>
    ): Day {
        return when {
            isWorkShiftDay(workSchedule, dateOfMonth) && !isCurrentMonthDay(
                dateOfMonth,
                activeDate
            ) -> WorkDay(
                day = dateOfMonth.dayOfMonth,
                month = dateOfMonth.monthValue,
                year = dateOfMonth.year,
                today = false,
                currentMonth = false
            )

            !isWorkShiftDay(workSchedule, dateOfMonth) && !isCurrentMonthDay(
                dateOfMonth,
                activeDate
            ) -> WeekendDay(
                day = dateOfMonth.dayOfMonth,
                month = dateOfMonth.monthValue,
                year = dateOfMonth.year,
                today = false,
                currentMonth = false
            )

            isWorkShiftDay(workSchedule, dateOfMonth) && isToday(dateOfMonth) ->
                WorkDay(
                    day = dateOfMonth.dayOfMonth,
                    month = dateOfMonth.monthValue,
                    year = dateOfMonth.year,
                    today = true,
                    currentMonth = true
                )

            !isWorkShiftDay(workSchedule, dateOfMonth) && isToday(dateOfMonth) ->
                WeekendDay(
                    day = dateOfMonth.dayOfMonth,
                    month = dateOfMonth.monthValue,
                    year = dateOfMonth.year,
                    today = true,
                    currentMonth = true
                )

            isWorkShiftDay(workSchedule, dateOfMonth) && isCurrentMonthDay(
                dateOfMonth,
                activeDate
            ) ->
                WorkDay(
                    day = dateOfMonth.dayOfMonth,
                    month = dateOfMonth.monthValue,
                    year = dateOfMonth.year,
                    today = false,
                    currentMonth = true
                )

            else -> WeekendDay(
                day = dateOfMonth.dayOfMonth,
                month = dateOfMonth.monthValue,
                year = dateOfMonth.year,
                today = false,
                currentMonth = true
            )
        }
    }

    private fun isCurrentMonthDay(dateOnMonth: LocalDate, date: LocalDate): Boolean {
        return dateOnMonth.monthValue == date.monthValue
    }

    private fun isToday(dateInMonth: LocalDate): Boolean {
        val today = dateNowContainer.getDate()
        return dateInMonth == today
    }

    private fun isWorkShiftDay(workDates: Set<LocalDate>, dateInMonth: LocalDate): Boolean {
        return workDates.contains(dateInMonth)
    }
}