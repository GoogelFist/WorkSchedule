package com.github.googelfist.workshedule.domain.monthgenerator.schedulecreator

import com.github.googelfist.workshedule.domain.Repository
import com.github.googelfist.workshedule.domain.models.ScheduleTypeState
import java.time.LocalDate
import javax.inject.Inject

class ScheduleCreatorImpl @Inject constructor(private val repository: Repository) :
    ScheduleCreator {

    override suspend fun createWorkSchedule(date: LocalDate): Set<LocalDate> {
        val scheduleType = repository.loadScheduleType()
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
        var lowDate = date.minusDays(RANGE_HALF_LENGTH)
        val dateSet = mutableSetOf<LocalDate>()

        (ZERO..RANGE_LENGTH step step.toInt()).forEach { _ ->
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

        private const val RANGE_LENGTH = 64
        private const val RANGE_HALF_LENGTH = RANGE_LENGTH / 2L
        private const val ZERO = 0
    }
}