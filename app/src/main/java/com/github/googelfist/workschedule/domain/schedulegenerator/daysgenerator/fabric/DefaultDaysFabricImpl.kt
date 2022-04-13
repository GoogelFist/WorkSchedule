package com.github.googelfist.workschedule.domain.schedulegenerator.daysgenerator.fabric

import com.github.googelfist.workschedule.domain.schedulegenerator.datecontainer.DateContainer
import com.github.googelfist.workschedule.domain.schedulegenerator.models.Day
import com.github.googelfist.workschedule.domain.schedulegenerator.models.DefaultDay
import java.time.LocalDate
import javax.inject.Inject

class DefaultDaysFabricImpl @Inject constructor(private val dateContainer: DateContainer) :
    DaysFabric {

    override fun getDay(dateOfMonth: LocalDate, activeDate: LocalDate): Day {

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

    private fun isCurrentMonthDay(dateOnMonth: LocalDate, date: LocalDate): Boolean {
        return dateOnMonth.monthValue == date.monthValue
    }

    private fun isToday(dateInMonth: LocalDate): Boolean {
        val today = dateContainer.getDateNow()
        return dateInMonth == today
    }
}
