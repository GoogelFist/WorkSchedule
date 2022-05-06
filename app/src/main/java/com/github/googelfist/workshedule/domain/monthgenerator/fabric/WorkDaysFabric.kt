package com.github.googelfist.workshedule.domain.monthgenerator.fabric

import com.github.googelfist.workshedule.domain.models.day.Day
import com.github.googelfist.workshedule.domain.models.day.WeekendDay
import com.github.googelfist.workshedule.domain.models.day.WeekendDaySleepOff
import com.github.googelfist.workshedule.domain.models.day.WorkDay
import com.github.googelfist.workshedule.domain.models.day.WorkNight
import com.github.googelfist.workshedule.domain.monthgenerator.DateNowContainer
import java.time.LocalDate
import javax.inject.Inject

class WorkDaysFabric @Inject constructor(private val dateNowContainer: DateNowContainer) {

    // TODO: too many conditions
    fun getDay(
        dateOfMonth: LocalDate,
        activeDate: LocalDate,
        workSchedule: Map<LocalDate, Int>
    ): Day {

        return when {
            getTypeShiftDay(workSchedule, dateOfMonth) == WORK_DAY && !isCurrentMonthDay(
                dateOfMonth,
                activeDate
            ) -> WorkDay(
                day = dateOfMonth.dayOfMonth,
                month = dateOfMonth.monthValue,
                year = dateOfMonth.year,
                today = false,
                currentMonth = false
            )

            getTypeShiftDay(workSchedule, dateOfMonth) == NIGHT_WORK_DAY && !isCurrentMonthDay(
                dateOfMonth,
                activeDate
            ) -> WorkNight(
                day = dateOfMonth.dayOfMonth,
                month = dateOfMonth.monthValue,
                year = dateOfMonth.year,
                today = false,
                currentMonth = false
            )

            getTypeShiftDay(workSchedule, dateOfMonth) == WEEKEND_DAY && !isCurrentMonthDay(
                dateOfMonth,
                activeDate
            ) -> WeekendDay(
                day = dateOfMonth.dayOfMonth,
                month = dateOfMonth.monthValue,
                year = dateOfMonth.year,
                today = false,
                currentMonth = false
            )

            getTypeShiftDay(
                workSchedule,
                dateOfMonth
            ) == SLEEP_OFF_WEEKEND_DAY && !isCurrentMonthDay(
                dateOfMonth,
                activeDate
            ) -> WeekendDaySleepOff(
                day = dateOfMonth.dayOfMonth,
                month = dateOfMonth.monthValue,
                year = dateOfMonth.year,
                today = false,
                currentMonth = false
            )

            getTypeShiftDay(workSchedule, dateOfMonth) == WORK_DAY && isToday(dateOfMonth) ->
                WorkDay(
                    day = dateOfMonth.dayOfMonth,
                    month = dateOfMonth.monthValue,
                    year = dateOfMonth.year,
                    today = true,
                    currentMonth = true
                )

            getTypeShiftDay(workSchedule, dateOfMonth) == NIGHT_WORK_DAY && isToday(dateOfMonth) ->
                WorkNight(
                    day = dateOfMonth.dayOfMonth,
                    month = dateOfMonth.monthValue,
                    year = dateOfMonth.year,
                    today = true,
                    currentMonth = true
                )

            getTypeShiftDay(workSchedule, dateOfMonth) == WEEKEND_DAY && isToday(dateOfMonth) ->
                WeekendDay(
                    day = dateOfMonth.dayOfMonth,
                    month = dateOfMonth.monthValue,
                    year = dateOfMonth.year,
                    today = true,
                    currentMonth = true
                )

            getTypeShiftDay(workSchedule, dateOfMonth) == SLEEP_OFF_WEEKEND_DAY && isToday(
                dateOfMonth
            ) ->
                WeekendDaySleepOff(
                    day = dateOfMonth.dayOfMonth,
                    month = dateOfMonth.monthValue,
                    year = dateOfMonth.year,
                    today = true,
                    currentMonth = true
                )

            getTypeShiftDay(workSchedule, dateOfMonth) == WORK_DAY && isCurrentMonthDay(
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

            getTypeShiftDay(workSchedule, dateOfMonth) == NIGHT_WORK_DAY && isCurrentMonthDay(
                dateOfMonth,
                activeDate
            ) ->
                WorkNight(
                    day = dateOfMonth.dayOfMonth,
                    month = dateOfMonth.monthValue,
                    year = dateOfMonth.year,
                    today = false,
                    currentMonth = true
                )

            getTypeShiftDay(workSchedule, dateOfMonth) == WEEKEND_DAY && isCurrentMonthDay(
                dateOfMonth,
                activeDate
            ) ->
                WeekendDay(
                    day = dateOfMonth.dayOfMonth,
                    month = dateOfMonth.monthValue,
                    year = dateOfMonth.year,
                    today = false,
                    currentMonth = true
                )

            else -> WeekendDaySleepOff(
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

    private fun getTypeShiftDay(workDates: Map<LocalDate, Int>, dateInMonth: LocalDate): Int {
        return workDates[dateInMonth]
            ?: throw RuntimeException("Date is not work shift in schedule")
    }

    companion object {
        // TODO: object constant helper
        private const val WORK_DAY = 1
        private const val NIGHT_WORK_DAY = 2
        private const val SLEEP_OFF_WEEKEND_DAY = 3
        private const val WEEKEND_DAY = 4
    }
}