package com.github.googelfist.workshedule.domain.monthgenerator

import com.github.googelfist.workshedule.domain.Repository
import com.github.googelfist.workshedule.domain.formatter.DateFormatter
import com.github.googelfist.workshedule.domain.models.ScheduleState
import com.github.googelfist.workshedule.domain.monthgenerator.daygenerator.DaysGenerator
import java.time.LocalDate
import javax.inject.Inject

class ScheduleGeneratorImpl @Inject constructor(
    private val dateNowContainer: DateNowContainer,
    private val formatter: DateFormatter,
    private val daysGenerator: DaysGenerator,
    private val repository: Repository
) : ScheduleGenerator {

    private var date = dateNowContainer.getDate()

    override suspend fun getCurrentMonthState(): ScheduleState {
        date = dateNowContainer.getDate()

        return getScheduleState(date)
    }

    override suspend fun getPreviousMonthState(): ScheduleState {
        date = date.minusMonths(ONE_VALUE)

        return getScheduleState(date)
    }

    override suspend fun getNextMonthState(): ScheduleState {
        date = date.plusMonths(ONE_VALUE)

        return getScheduleState(date)
    }

    private suspend fun getScheduleState(date: LocalDate): ScheduleState {
        val formattedDate = formatter.formatDate(date)
        repository.getFromCache(formattedDate)?.let {
            return ScheduleState.GeneratedState(formattedDate, it)
        }

        preloadMonthStates(date)

        val dayList = repository.getFromCache(formattedDate)
            ?: throw RuntimeException("Cache is not contains list")

        return ScheduleState.GeneratedState(formattedDate, dayList)
    }

    private suspend fun preloadMonthStates(currentDate: LocalDate) {
        var startDate = currentDate.minusMonths(MONTH_RANGE_FROM_MIDDLE)

        repeat(REPEAT_MONTH_RANGE) {
            val formattedDate = formatter.formatDate(startDate)
            val dayList = daysGenerator.getDays(startDate)
            repository.putToCache(formattedDate, dayList)
            startDate = startDate.plusMonths(ONE_VALUE)
        }
    }

    companion object {
        private const val ONE_VALUE = 1L

        private const val REPEAT_MONTH_RANGE = 6

        private const val MONTH_RANGE_FROM_MIDDLE = 3L
    }
}