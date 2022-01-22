package com.github.googelfist.workschedule.data.schedulegenerator.fabric

import com.github.googelfist.workschedule.data.schedulegenerator.models.Day
import com.github.googelfist.workschedule.data.schedulegenerator.models.workday.WorkActiveDay
import com.github.googelfist.workschedule.data.schedulegenerator.models.workday.WorkInActiveDay
import com.github.googelfist.workschedule.data.schedulegenerator.models.workday.WorkToday
import com.github.googelfist.workschedule.data.schedulegenerator.scheduletype.ScheduleSetup
import com.github.googelfist.workschedule.domain.PreferenceRepository
import java.time.LocalDate

class WorkDaysFabricImpl : WorkDaysFabric {

    override fun getWorkDay(
        dateInMonth: LocalDate,
        activeDate: LocalDate,
        preferenceRepository: PreferenceRepository,
        scheduleSetup: ScheduleSetup
    ): Day {
        val firstWorkDay = getFirstWorkDate(preferenceRepository)
        val actualFirstWorkDay = scheduleSetup.getActualFirstDate(dateInMonth, firstWorkDay)
        val workSchedule = scheduleSetup.getWorkSchedule(actualFirstWorkDay)

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

    private fun getFirstWorkDate(preferenceRepository: PreferenceRepository): LocalDate {
        val firstWorkDatePreference = preferenceRepository.loadPreference().firstWorkDate
        return LocalDate.parse(firstWorkDatePreference)
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
