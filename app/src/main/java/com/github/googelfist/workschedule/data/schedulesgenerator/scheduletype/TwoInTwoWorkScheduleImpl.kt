package com.github.googelfist.workschedule.data.schedulesgenerator.scheduletype

import java.time.LocalDate

class TwoInTwoWorkScheduleImpl : ScheduleType {

    override fun getWorkSchedule(date: LocalDate): Set<LocalDate> {
        var lowDate = date.minusDays(RANGE_HALF_LENGTH)
        val dateSet = mutableSetOf<LocalDate>()

        (ZERO..RANGE_LENGTH step STEP).forEach { _ ->
            dateSet.add(lowDate)
            dateSet.add(lowDate.plusDays(ONE_VALUE))
            lowDate = lowDate.plusDays(STEP.toLong())
        }
        return dateSet
    }

    companion object {
        private const val STEP = 4
        private const val ONE_VALUE = 1L
        private const val RANGE_LENGTH = 80
        private const val RANGE_HALF_LENGTH = RANGE_LENGTH / 2L
        private const val ZERO = 0
    }
}
