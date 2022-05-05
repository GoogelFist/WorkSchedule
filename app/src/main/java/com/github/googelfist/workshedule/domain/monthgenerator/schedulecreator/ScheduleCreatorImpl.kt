package com.github.googelfist.workshedule.domain.monthgenerator.schedulecreator

import com.github.googelfist.workshedule.domain.models.ScheduleTypeState
import java.time.LocalDate
import javax.inject.Inject

class ScheduleCreatorImpl @Inject constructor() :
    ScheduleCreator {

    override suspend fun createWorkSchedule(
        scheduleType: ScheduleTypeState,
        date: LocalDate
    ): Map<LocalDate, Int> {
        return when (scheduleType) {
            is ScheduleTypeState.TwoInTwo -> {
                val firstWorkDate = scheduleType.firstWorkDate
                val step = scheduleType.dayCycleStep
                val actualFirstWorkDate = getActualFirstWorkDay(date, firstWorkDate, step)
                getWorkSchedule(actualFirstWorkDate, step)
            }
            else -> emptyMap()
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

    private fun getWorkSchedule(date: LocalDate, step: Long): Map<LocalDate, Int> {
        val map = mutableMapOf<LocalDate, Int>()

        val startDate = date.minusDays(MONTH_RANGE_FROM_MIDDLE)
        val endDate = date.plusDays(MONTH_RANGE_FROM_MIDDLE)

        var lowDate = date
        while (lowDate > startDate) {
            lowDate = lowDate.minusDays(step)
        }

        if (step == TWO_IN_TWO_STEP) {
            while (lowDate < endDate) {
                repeat(step.toInt() / TWO_VALUE) {
                    map[lowDate] = WORK_DAY
                    lowDate = lowDate.plusDays(ONE_VALUE)

                    map[lowDate] = NIGHT_WORK_DAY
                    lowDate = lowDate.plusDays(ONE_VALUE)

                    map[lowDate] = SLEEP_OFF_WEEKEND_DAY
                    lowDate = lowDate.plusDays(ONE_VALUE)

                    map[lowDate] = WEEKEND_DAY
                    lowDate = lowDate.plusDays(ONE_VALUE)
                }
            }
        } else {
            throw RuntimeException("Incorrect step")
        }

        return map
    }

    companion object {
        private const val ONE_VALUE = 1L
        private const val TWO_VALUE = 2

        // TODO: object constant helper
        private const val WORK_DAY = 1
        private const val NIGHT_WORK_DAY = 2
        private const val SLEEP_OFF_WEEKEND_DAY = 3
        private const val WEEKEND_DAY = 4

        private const val TWO_IN_TWO_STEP = 4L

        private const val MONTH_RANGE_FROM_MIDDLE = 45L
    }
}