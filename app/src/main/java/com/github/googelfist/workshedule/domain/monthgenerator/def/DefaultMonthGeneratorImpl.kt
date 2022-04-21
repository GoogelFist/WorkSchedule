package com.github.googelfist.workshedule.domain.monthgenerator.def

import com.github.googelfist.workshedule.domain.formatter.DateFormatter
import com.github.googelfist.workshedule.domain.models.month.DefaultMonth
import com.github.googelfist.workshedule.domain.models.month.Month
import com.github.googelfist.workshedule.domain.monthgenerator.DateNowContainer
import com.github.googelfist.workshedule.domain.monthgenerator.def.daysgenerator.DefaultDaysGenerator
import javax.inject.Inject

class DefaultMonthGeneratorImpl @Inject constructor(
    private val dateNowContainer: DateNowContainer,
    private val formatter: DateFormatter,
    private val defaultDaysGenerator: DefaultDaysGenerator
) : DefaultMonthGenerator {

    private var date = dateNowContainer.getDate()

    override fun generateCurrentMonth(): Month {
        date = dateNowContainer.getDate()
        val dayList = defaultDaysGenerator.generateDays(date)
        val formattedDate = formatter.formatDate(date)
        return DefaultMonth(formattedDate, dayList)
    }

    override fun generatePreviousMonth(): Month {
        date = date.minusMonths(ONE_VALUE)
        val dayList = defaultDaysGenerator.generateDays(date)
        val formattedDate = formatter.formatDate(date)
        return DefaultMonth(formattedDate, dayList)
    }

    override fun generateNextMonth(): Month {
        date = date.plusMonths(ONE_VALUE)
        val dayList = defaultDaysGenerator.generateDays(date)
        val formattedDate = formatter.formatDate(date)
        return DefaultMonth(formattedDate, dayList)
    }

    companion object {
        private const val ONE_VALUE = 1L
    }
}