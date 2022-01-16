package com.github.googelfist.workschedule.data.defaultschedulegenerator.fabric

import com.github.googelfist.workschedule.domain.models.days.ActiveDay
import com.github.googelfist.workschedule.domain.models.days.Day
import com.github.googelfist.workschedule.domain.models.days.InActiveDay
import com.github.googelfist.workschedule.domain.models.days.Today
import java.time.LocalDate

class DefaultDaysFabricImpl : DefaultDaysFabric {

    override fun getDay(dateInMonth: LocalDate, activeDate: LocalDate): Day {

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

    private fun isInActiveDay(firstDate: LocalDate, date: LocalDate): Boolean {
        return firstDate.monthValue != date.monthValue
    }

    private fun isToday(firstDate: LocalDate): Boolean {
        val today = LocalDate.now()
        return firstDate == today
    }
}
