package com.github.googelfist.workshedule.domain.monthgenerator

import com.github.googelfist.workshedule.domain.formatter.DateFormatter
import com.github.googelfist.workshedule.domain.models.ScheduleState
import com.github.googelfist.workshedule.domain.monthgenerator.daygenerator.DaysGenerator
import javax.inject.Inject

class ScheduleGeneratorImpl @Inject constructor(
    private val dateNowContainer: DateNowContainer,
    private val formatter: DateFormatter,
    private val daysGenerator: DaysGenerator
) : ScheduleGenerator {

    private var date = dateNowContainer.getDate()

    override suspend fun generateCurrentMonth(): ScheduleState {
        date = dateNowContainer.getDate()
        val dayList = daysGenerator.getDays(date)
        val formattedDate = formatter.formatDate(date)
        return ScheduleState.GeneratedState(formattedDate, dayList)
    }

    override suspend fun generatePreviousMonth(): ScheduleState {
        date = date.minusMonths(ONE_VALUE)
        val dayList = daysGenerator.getDays(date)
        val formattedDate = formatter.formatDate(date)
        return ScheduleState.GeneratedState(formattedDate, dayList)
    }

    override suspend fun generateNextMonth(): ScheduleState {
        date = date.plusMonths(ONE_VALUE)
        val dayList = daysGenerator.getDays(date)
        val formattedDate = formatter.formatDate(date)
        return ScheduleState.GeneratedState(formattedDate, dayList)
    }

    companion object {
        private const val ONE_VALUE = 1L
    }
}