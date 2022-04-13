package com.github.googelfist.workschedule.domain.schedulegenerator.fabric

import com.github.googelfist.workschedule.domain.schedulegenerator.datecontainer.DateContainer
import com.github.googelfist.workschedule.domain.schedulegenerator.models.Day
import com.github.googelfist.workschedule.domain.schedulegenerator.models.DefaultDay
import java.time.LocalDate
import javax.inject.Inject

class DefaultDaysFabricImpl @Inject constructor(private val dateContainer: DateContainer) :
    DaysFabric {

    override fun getDay(dateInMonth: LocalDate, activeDate: LocalDate): Day {

        return when {
            isCurrentMonthDay(dateInMonth, activeDate) -> DefaultDay(
                day = dateInMonth.dayOfMonth,
                month = dateInMonth.monthValue,
                year = dateInMonth.year,
                today = false,
                currentMonth = true
            )

            isToday(dateInMonth) -> DefaultDay(
                day = dateInMonth.dayOfMonth,
                month = dateInMonth.monthValue,
                year = dateInMonth.year,
                today = true,
                currentMonth = true
            )

            else -> DefaultDay(
                day = dateInMonth.dayOfMonth,
                month = dateInMonth.monthValue,
                year = dateInMonth.year,
                today = false,
                currentMonth = false
            )
        }
    }

    private fun isCurrentMonthDay(dateInMonth: LocalDate, date: LocalDate): Boolean {
        return dateInMonth.monthValue == date.monthValue
    }

    private fun isToday(dateInMonth: LocalDate): Boolean {
        val today = dateContainer.getDateNow()
        return dateInMonth == today
    }
}
