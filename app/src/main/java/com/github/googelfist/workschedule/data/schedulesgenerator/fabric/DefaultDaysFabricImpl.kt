package com.github.googelfist.workschedule.data.schedulesgenerator.fabric

import com.github.googelfist.workschedule.domain.models.days.ActiveDay
import com.github.googelfist.workschedule.domain.models.days.Day
import com.github.googelfist.workschedule.domain.models.days.InActiveDay
import com.github.googelfist.workschedule.domain.models.days.Today
import java.time.LocalDate

class DefaultDaysFabricImpl : DefaultDaysFabric {

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

    private fun isInActiveDay(dateInMonth: LocalDate, date: LocalDate): Boolean {
        return dateInMonth.monthValue != date.monthValue
    }

    private fun isToday(dateInMonth: LocalDate): Boolean {
        val today = LocalDate.now()
        return dateInMonth == today
    }
}
