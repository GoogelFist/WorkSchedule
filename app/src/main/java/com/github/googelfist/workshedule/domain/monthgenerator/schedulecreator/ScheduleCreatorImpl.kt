package com.github.googelfist.workshedule.domain.monthgenerator.schedulecreator

import com.github.googelfist.workshedule.domain.models.ScheduleTypeState
import java.time.LocalDate
import javax.inject.Inject

class ScheduleCreatorImpl @Inject constructor() :
    ScheduleCreator {

    override suspend fun createWorkSchedule(
        scheduleType: ScheduleTypeState,
        date: LocalDate
    ): Set<LocalDate> {
        return when (scheduleType) {
            is ScheduleTypeState.TwoInTwo -> {
                val firstWorkDate = scheduleType.firstWorkDate
                val step = scheduleType.dayCycleStep
                val actualFirstWorkDate = getActualFirstWorkDay(date, firstWorkDate, step)
                getWorkSchedule(actualFirstWorkDate, step)
            }
            else -> emptySet()
        }
    }

    private fun getActualFirstWorkDay(
        activeDate: LocalDate,
        firstWorkDate: LocalDate,
        step: Long
    ): LocalDate {
        var curDate = firstWorkDate
        when {
            curDate == activeDate -> return activeDate
            curDate < activeDate -> while (curDate < activeDate) {
                curDate = curDate.plusDays(step)
            }
            curDate > activeDate -> while (curDate > activeDate) {
                curDate = curDate.minusDays(step)
            }
        }
        return curDate
    }

    private fun getWorkSchedule(date: LocalDate, step: Long): Set<LocalDate> {
        val dateSet = mutableSetOf<LocalDate>()

        val startDate = date.minusDays(MONTH_RANGE_FROM_MIDDLE)
        val endDate = date.plusDays(MONTH_RANGE_FROM_MIDDLE)

        var lowDate = date
        while (lowDate > startDate) {
            lowDate = lowDate.minusDays(step)
        }

        while (lowDate < endDate) {
            var workDate = lowDate
            repeat(step.toInt() / TWO_VALUE) {
                dateSet.add(workDate)
                workDate = workDate.plusDays(ONE_VALUE)
            }
            lowDate = lowDate.plusDays(step)
        }
        return dateSet
    }

    companion object {
        private const val ONE_VALUE = 1L
        private const val TWO_VALUE = 2

        private const val MONTH_RANGE_FROM_MIDDLE = 45L
    }
}