package com.github.googelfist.workschedule.data.schedulegenerator.fabric

import com.github.googelfist.workschedule.domain.models.days.Day
import com.github.googelfist.workschedule.domain.models.days.defaultday.DefaultActiveDay
import com.github.googelfist.workschedule.domain.models.days.defaultday.DefaultInActiveDay
import com.github.googelfist.workschedule.domain.models.days.defaultday.DefaultToday
import java.time.LocalDate

class DefaultDaysFabricImpl : DaysFabric {

    override fun getDay(dateInMonth: LocalDate, activeDate: LocalDate): Day {

        return when {
            isInActiveDay(dateInMonth, activeDate) -> DefaultInActiveDay(
                value = dateInMonth.dayOfMonth,
                month = dateInMonth.monthValue,
                year = dateInMonth.year
            )

            isToday(dateInMonth) -> DefaultToday(
                value = dateInMonth.dayOfMonth,
                month = dateInMonth.monthValue,
                year = dateInMonth.year
            )

            else -> DefaultActiveDay(
                value = dateInMonth.dayOfMonth,
                month = dateInMonth.monthValue,
                year = dateInMonth.year
            )
        }
    }

    private fun isInActiveDay(dateInMonth: LocalDate, date: LocalDate): Boolean {
        return dateInMonth.monthValue != date.monthValue
    }

    private fun isToday(dateInMonth: LocalDate): Boolean {
        val today = LocalDate.now()
        return dateInMonth == today
    }
}
