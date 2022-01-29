package com.github.googelfist.workschedule.domain.schedulegenerator.fabric

import com.github.googelfist.workschedule.domain.schedulegenerator.datecontainer.DateContainer
import com.github.googelfist.workschedule.domain.schedulegenerator.models.Day
import com.github.googelfist.workschedule.domain.schedulegenerator.models.defaultday.DefaultActiveDay
import com.github.googelfist.workschedule.domain.schedulegenerator.models.defaultday.DefaultInActiveDay
import com.github.googelfist.workschedule.domain.schedulegenerator.models.defaultday.DefaultToday
import java.time.LocalDate
import javax.inject.Inject

class DefaultDaysFabricImpl @Inject constructor(private val dateContainer: DateContainer) :
    DaysFabric {

    override fun getDay(dateInMonth: LocalDate, activeDate: LocalDate): Day {

        return when {
            isInActiveDay(dateInMonth, activeDate) -> DefaultInActiveDay(
                day = dateInMonth.dayOfMonth,
                month = dateInMonth.monthValue,
                year = dateInMonth.year
            )

            isToday(dateInMonth) -> DefaultToday(
                day = dateInMonth.dayOfMonth,
                month = dateInMonth.monthValue,
                year = dateInMonth.year
            )

            else -> DefaultActiveDay(
                day = dateInMonth.dayOfMonth,
                month = dateInMonth.monthValue,
                year = dateInMonth.year
            )
        }
    }

    private fun isInActiveDay(dateInMonth: LocalDate, date: LocalDate): Boolean {
        return dateInMonth.monthValue != date.monthValue
    }

    private fun isToday(dateInMonth: LocalDate): Boolean {
        val today = dateContainer.getDateNow()
        return dateInMonth == today
    }
}
