package com.github.googelfist.workshedule.domain.monthgenerator

import com.github.googelfist.workshedule.domain.formatter.DateFormatter
import com.github.googelfist.workshedule.domain.models.ScheduleState
import com.github.googelfist.workshedule.domain.monthgenerator.daygenerator.DaysGenerator
import java.time.LocalDate
import javax.inject.Inject

class ScheduleGeneratorImpl @Inject constructor(
    private val dateNowContainer: DateNowContainer,
    private val formatter: DateFormatter,
    private val daysGenerator: DaysGenerator,
    private val cache: ScheduleGeneratorCache
) : ScheduleGenerator {

    private var date = dateNowContainer.getDate()

    override suspend fun getCurrentMonthState(): ScheduleState {
        date = dateNowContainer.getDate()

        return getCurrentScheduleState(date)
    }

    override suspend fun getPreviousMonthState(): ScheduleState {
        date = date.minusMonths(ONE_VALUE)

        return getPreviousScheduleState(date)
    }

    override suspend fun getNextMonthState(): ScheduleState {
        date = date.plusMonths(ONE_VALUE)

        return getNextScheduleState(date)
    }

    private suspend fun getCurrentScheduleState(date: LocalDate): ScheduleState {
        val formattedDate = formatter.formatDateToSchedule(date)

        cache.clearCache()
        preloadCurrentMonthStates(date)

        val dayList = cache.getFromCache(formattedDate)
            ?: throw RuntimeException("Cache is not contains list")

        return ScheduleState.GeneratedState(formattedDate, dayList)
    }

    private suspend fun preloadCurrentMonthStates(currentDate: LocalDate) {
        var startDate = currentDate.minusMonths(REPEAT_PRELOAD_RANGE_DOWN_FROM_CURRENT)

        repeat(REPEAT_PRELOAD_TIMES_CURRENT) {
            val formattedDate = formatter.formatDateToSchedule(startDate)
            val dayList = daysGenerator.getDays(startDate)
            cache.putToCache(formattedDate, dayList)
            startDate = startDate.plusMonths(ONE_VALUE)
        }
    }

    private suspend fun getPreviousScheduleState(date: LocalDate): ScheduleState {
        val formattedDate = formatter.formatDateToSchedule(date)
        cache.getFromCache(formattedDate)?.let {
            return ScheduleState.GeneratedState(formattedDate, it)
        }

        preloadPreviousMonthsStates(date)

        val dayList = cache.getFromCache(formattedDate)
            ?: throw RuntimeException("Cache is not contains list")

        return ScheduleState.GeneratedState(formattedDate, dayList)
    }

    private suspend fun preloadPreviousMonthsStates(date: LocalDate) {
        var startDate = date.minusMonths(REPEAT_PRELOAD_RANGE)

        repeat(REPEAT_PRELOAD_TIMES) {
            val formattedDate = formatter.formatDateToSchedule(startDate)
            val dayList = daysGenerator.getDays(startDate)
            cache.putToCache(formattedDate, dayList)
            startDate = startDate.plusMonths(ONE_VALUE)
        }
    }

    private suspend fun getNextScheduleState(date: LocalDate): ScheduleState {
        val formattedDate = formatter.formatDateToSchedule(date)
        cache.getFromCache(formattedDate)?.let {
            return ScheduleState.GeneratedState(formattedDate, it)
        }

        preloadNextMonthsStates(date)

        val dayList = cache.getFromCache(formattedDate)
            ?: throw RuntimeException("Cache is not contains list")

        return ScheduleState.GeneratedState(formattedDate, dayList)
    }

    private suspend fun preloadNextMonthsStates(date: LocalDate) {
        var startDate = date

        repeat(REPEAT_PRELOAD_TIMES) {
            val formattedDate = formatter.formatDateToSchedule(startDate)
            val dayList = daysGenerator.getDays(startDate)
            cache.putToCache(formattedDate, dayList)
            startDate = startDate.plusMonths(ONE_VALUE)
        }
    }

    companion object {
        private const val ONE_VALUE = 1L

        private const val REPEAT_PRELOAD_RANGE = 4L
        private const val REPEAT_PRELOAD_TIMES = 5

        private const val REPEAT_PRELOAD_RANGE_DOWN_FROM_CURRENT = 2L
        private const val REPEAT_PRELOAD_TIMES_CURRENT = 6
    }
}