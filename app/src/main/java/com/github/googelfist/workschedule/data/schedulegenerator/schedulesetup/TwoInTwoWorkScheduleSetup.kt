package com.github.googelfist.workschedule.data.schedulegenerator.schedulesetup

import java.time.LocalDate

class TwoInTwoWorkScheduleSetup : ScheduleSetup {

    override fun getWorkSchedule(date: LocalDate): Set<LocalDate> {
        var lowDate = date.minusDays(RANGE_HALF_LENGTH)
        val dateSet = mutableSetOf<LocalDate>()

        (ZERO..RANGE_LENGTH step STEP.toInt()).forEach { _ ->
            dateSet.add(lowDate)
            dateSet.add(lowDate.plusDays(ONE_VALUE))
            lowDate = lowDate.plusDays(STEP)
        }
        return dateSet
    }

    override fun getActualFirstDate(activeDate: LocalDate, firstWorkDate: LocalDate): LocalDate {
        var curDate = firstWorkDate
        when {
            curDate == activeDate -> return activeDate
            curDate < activeDate -> while (curDate < activeDate) {
                curDate = curDate.plusDays(STEP)
            }
            curDate > activeDate -> while (curDate > activeDate) {
                curDate = curDate.minusDays(STEP)
            }
        }
        return curDate
    }

    companion object {
        private const val STEP = 4L
        private const val ONE_VALUE = 1L
        private const val RANGE_LENGTH = 80
        private const val RANGE_HALF_LENGTH = RANGE_LENGTH / 2L
        private const val ZERO = 0
    }
}
