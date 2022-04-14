package com.github.googelfist.workshedule.domain

import com.github.googelfist.workshedule.domain.daysgenerator.DateNowContainer
import com.github.googelfist.workshedule.domain.daysgenerator.DaysGenerator
import com.github.googelfist.workshedule.domain.formatter.DateFormatter
import com.github.googelfist.workshedule.domain.models.DefaultMonth
import com.github.googelfist.workshedule.domain.models.Month
import javax.inject.Inject

class DefaultMonthGeneratorImpl @Inject constructor(
    private val dateNowContainer: DateNowContainer,
    private val formatter: DateFormatter,
    private val daysGenerator: DaysGenerator
) : MonthGenerator {

    private var date = dateNowContainer.getDate()

    override fun generateCurrentMonth(): Month {
        date = dateNowContainer.getDate()
        val dayList = daysGenerator.generateDays(date)
        val formattedDate = formatter.formatDate(date)
        return DefaultMonth(formattedDate, dayList)
    }

    override fun generatePreviousMonth(): Month {
        date = date.minusMonths(ONE_VALUE)
        val dayList = daysGenerator.generateDays(date)
        val formattedDate = formatter.formatDate(date)
        return DefaultMonth(formattedDate, dayList)
    }

    override fun generateNextMonth(): Month {
        date = date.plusMonths(ONE_VALUE)
        val dayList = daysGenerator.generateDays(date)
        val formattedDate = formatter.formatDate(date)
        return DefaultMonth(formattedDate, dayList)
    }

    companion object {
        private const val ONE_VALUE = 1L
    }
}