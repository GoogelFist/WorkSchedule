package com.github.googelfist.workshedule.domain.monthgenerator.fabric

import com.github.googelfist.workshedule.domain.models.Day
import com.github.googelfist.workshedule.domain.models.DayType
import com.github.googelfist.workshedule.domain.monthgenerator.DateNowContainer
import java.time.LocalDate
import javax.inject.Inject

class DaysFabricImpl @Inject constructor(
    private val dateNowContainer: DateNowContainer
) : DaysFabric {

    override fun getDay(
        dayType: DayType,
        dateOfMonth: LocalDate,
        activeDate: LocalDate
    ): Day {

        val backgroundColor = dayType.backgroundColor
        val title = dayType.title

        return when {
            !isCurrentMonthDay(dateOfMonth, activeDate) ->
                Day(
                    day = dateOfMonth.dayOfMonth,
                    month = dateOfMonth.monthValue,
                    year = dateOfMonth.year,
                    today = false,
                    currentMonth = false,
                    backgroundColor = backgroundColor,
                    title = title
                )

            isToday(dateOfMonth) ->
                Day(
                    day = dateOfMonth.dayOfMonth,
                    month = dateOfMonth.monthValue,
                    year = dateOfMonth.year,
                    today = true,
                    currentMonth = true,
                    backgroundColor = backgroundColor,
                    title = title
                )

            else -> Day(
                day = dateOfMonth.dayOfMonth,
                month = dateOfMonth.monthValue,
                year = dateOfMonth.year,
                today = false,
                currentMonth = true,
                backgroundColor = backgroundColor,
                title = title
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
}