package com.github.googelfist.workshedule.domain.monthgenerator

import com.github.googelfist.workshedule.domain.formatter.DateFormatter
import com.github.googelfist.workshedule.domain.models.month.Month
import com.github.googelfist.workshedule.domain.models.month.WorkMonth
import com.github.googelfist.workshedule.domain.monthgenerator.daygenerator.DaysGenerator
import javax.inject.Inject

class MonthGeneratorImpl @Inject constructor(
    private val dateNowContainer: DateNowContainer,
    private val formatter: DateFormatter,
    private val daysGenerator: DaysGenerator
) : MonthGenerator {

    private var date = dateNowContainer.getDate()

    override suspend fun generateCurrentMonth(): Month {
        date = dateNowContainer.getDate()
        val dayList = daysGenerator.getDays(date)
        val formattedDate = formatter.formatDate(date)
        return WorkMonth(formattedDate, dayList)
    }

    override suspend fun generatePreviousMonth(): Month {
        date = date.minusMonths(ONE_VALUE)
        val dayList = daysGenerator.getDays(date)
        val formattedDate = formatter.formatDate(date)
        return WorkMonth(formattedDate, dayList)
    }

    override suspend fun generateNextMonth(): Month {
        date = date.plusMonths(ONE_VALUE)
        val dayList = daysGenerator.getDays(date)
        val formattedDate = formatter.formatDate(date)
        return WorkMonth(formattedDate, dayList)
    }

    companion object {
        private const val ONE_VALUE = 1L
    }
}