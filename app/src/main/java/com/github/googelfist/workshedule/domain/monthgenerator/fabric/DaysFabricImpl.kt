package com.github.googelfist.workshedule.domain.monthgenerator.fabric

import com.github.googelfist.workshedule.domain.models.day.Day
import com.github.googelfist.workshedule.domain.models.day.DefaultDay
import com.github.googelfist.workshedule.domain.models.day.NightWorkDay
import com.github.googelfist.workshedule.domain.models.day.SleepOffWeekendDay
import com.github.googelfist.workshedule.domain.models.day.WeekendDay
import com.github.googelfist.workshedule.domain.models.day.WorkDay
import com.github.googelfist.workshedule.domain.monthgenerator.DateNowContainer
import java.time.LocalDate
import javax.inject.Inject

class DaysFabricImpl @Inject constructor(
    private val dateNowContainer: DateNowContainer
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

    // TODO: too many condition
    override suspend fun getWorkDay(
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
            ) -> NightWorkDay(
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
            ) -> SleepOffWeekendDay(
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
                NightWorkDay(
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
                SleepOffWeekendDay(
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
                NightWorkDay(
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

            else -> SleepOffWeekendDay(
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