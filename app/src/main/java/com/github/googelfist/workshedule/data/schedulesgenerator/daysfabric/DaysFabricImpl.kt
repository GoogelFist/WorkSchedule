package com.github.googelfist.workshedule.data.schedulesgenerator.daysfabric

import com.github.googelfist.workshedule.domain.models.days.ActiveDay
import com.github.googelfist.workshedule.domain.models.days.Day
import com.github.googelfist.workshedule.domain.models.days.InActiveDay
import com.github.googelfist.workshedule.domain.models.days.Today
import java.time.LocalDate

class DaysFabricImpl : DaysFabric {

    override fun getDay(firstDate: LocalDate, date: LocalDate): Day {
        val today = LocalDate.now()
        return when {
            isInActiveDay(firstDate, date) -> InActiveDay(
                value = firstDate.dayOfMonth,
                month = firstDate.monthValue,
                year = firstDate.year
            )

            isToday(firstDate, today) ->
                Today(
                    value = firstDate.dayOfMonth,
                    month = firstDate.monthValue,
                    year = firstDate.year
                )

            else -> ActiveDay(
                value = firstDate.dayOfMonth,
                month = firstDate.monthValue,
                year = firstDate.year
            )
        }
    }

    private fun isInActiveDay(firstDate: LocalDate, date: LocalDate): Boolean {
        return firstDate.monthValue != date.monthValue
    }

    private fun isToday(firstDate: LocalDate, today: LocalDate): Boolean {
        return firstDate.monthValue == today.monthValue && firstDate.dayOfMonth == today.dayOfMonth
    }
}
