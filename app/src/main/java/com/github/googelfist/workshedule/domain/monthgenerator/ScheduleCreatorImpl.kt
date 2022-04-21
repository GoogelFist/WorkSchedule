package com.github.googelfist.workshedule.domain.monthgenerator

import com.github.googelfist.workshedule.domain.Repository
import java.time.LocalDate
import javax.inject.Inject

class ScheduleCreatorImpl @Inject constructor(private val repository: Repository) :
    ScheduleCreator {

    override suspend fun createTwoInTwoSchedule(date: LocalDate): Set<LocalDate> {
        val firstWorkDate = repository.loadFirstWorkDate()
        val actualFirstWorkDate = getActualFirstWorkDay(date, firstWorkDate, TWO_IN_TWO_STEP)
        return getWorkSchedule(actualFirstWorkDate, TWO_IN_TWO_STEP)
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
            dateSet.add(lowDate)
            dateSet.add(lowDate.plusDays(ONE_VALUE))
            lowDate = lowDate.plusDays(step)
        }
        return dateSet
    }


    companion object {
        private const val ONE_VALUE = 1L

        private const val TWO_IN_TWO_STEP = 4L
        private const val RANGE_LENGTH = 64
        private const val RANGE_HALF_LENGTH = RANGE_LENGTH / 2L
        private const val ZERO = 0
    }
}