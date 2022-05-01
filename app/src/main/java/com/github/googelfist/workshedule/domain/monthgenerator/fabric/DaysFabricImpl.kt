package com.github.googelfist.workshedule.domain.monthgenerator.fabric

import com.github.googelfist.workshedule.domain.models.day.Day
import com.github.googelfist.workshedule.domain.models.day.DefaultDay
import com.github.googelfist.workshedule.domain.models.day.WeekendDay
import com.github.googelfist.workshedule.domain.models.day.WorkDay
import com.github.googelfist.workshedule.domain.monthgenerator.DateNowContainer
import com.github.googelfist.workshedule.domain.monthgenerator.schedulecreator.ScheduleCreator
import java.time.LocalDate
import javax.inject.Inject

class DaysFabricImpl @Inject constructor(
    private val dateNowContainer: DateNowContainer,
    private val scheduleCreator: ScheduleCreator
) : DaysFabric {

    override fun getDefaultDay(dateOfMonth: LocalDate, activeDate: LocalDate): Day {

        return when {
            !isCurrentMonthDay(dateOfMonth, activeDate) -> DefaultDay(
                day = dateOfMonth.dayOfMonth,
                month = dateOfMonth.monthValue,
                year = dateOfMonth.year,
                today = false,
                currentMonth = false
            )

            isToday(dateOfMonth) -> DefaultDay(
                day = dateOfMonth.dayOfMonth,
                month = dateOfMonth.monthValue,
                year = dateOfMonth.year,
                today = true,
                currentMonth = true
            )

            else -> DefaultDay(
                day = dateOfMonth.dayOfMonth,
                month = dateOfMonth.monthValue,
                year = dateOfMonth.year,
                today = false,
                currentMonth = true
            )
        }
    }

    override suspend fun getWorkDay(dateOfMonth: LocalDate, activeDate: LocalDate): Day {

        val workSchedule = scheduleCreator.createWorkSchedule(dateOfMonth)

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