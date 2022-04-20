package com.github.googelfist.workshedule.domain.monthgenerator.twointwo

import com.github.googelfist.workshedule.domain.formatter.DateFormatter
import com.github.googelfist.workshedule.domain.models.month.Month
import com.github.googelfist.workshedule.domain.models.month.WorkMonth
import com.github.googelfist.workshedule.domain.monthgenerator.DateNowContainer
import com.github.googelfist.workshedule.domain.monthgenerator.twointwo.daygenerator.WorkTwoInTwoDaysGenerator
import java.time.LocalDate
import javax.inject.Inject

class WorkTwoInTwoMonthGeneratorImpl @Inject constructor(
    private val dateNowContainer: DateNowContainer,
    private val formatter: DateFormatter,
    private val workDaysGenerator: WorkTwoInTwoDaysGenerator
) : WorkTwoInTwoMonthGenerator {

    private var date = dateNowContainer.getDate()

    override fun generateCurrentMonth(firstWorkDate: LocalDate): Month {
        date = dateNowContainer.getDate()
        val dayList = workDaysGenerator.generateDays(date, firstWorkDate)
        val formattedDate = formatter.formatDate(date)
        return WorkMonth(formattedDate, dayList)
    }

    override fun generatePreviousMonth(firstWorkDate: LocalDate): Month {
        date = date.minusMonths(ONE_VALUE)
        val dayList = workDaysGenerator.generateDays(date, firstWorkDate)
        val formattedDate = formatter.formatDate(date)
        return WorkMonth(formattedDate, dayList)
    }

    override fun generateNextMonth(firstWorkDate: LocalDate): Month {
        date = date.plusMonths(ONE_VALUE)
        val dayList = workDaysGenerator.generateDays(date, firstWorkDate)
        val formattedDate = formatter.formatDate(date)
        return WorkMonth(formattedDate, dayList)
    }

    companion object {
        private const val ONE_VALUE = 1L
    }
}