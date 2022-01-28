package com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.schedulesetup

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class TwoInTwoWorkScheduleSetupTest {

    @Test
    fun `should return work schedule`() {
        val date = LocalDate.parse(ACTUAL_DATE)
        var lowDate = date.minusDays(RANGE_HALF_LENGTH)
        val dateSet = mutableSetOf<LocalDate>()

        (ZERO..RANGE_LENGTH step STEP.toInt()).forEach { _ ->
            dateSet.add(lowDate)
            dateSet.add(lowDate.plusDays(ONE_VALUE))
            lowDate = lowDate.plusDays(STEP)
        }

        val scheduleSetup = TwoInTwoWorkScheduleSetup()

        val expectedDataSet = dateSet.sorted()
        val actualDataSet = scheduleSetup.getWorkSchedule(date).sorted()

        Assertions.assertEquals(expectedDataSet, actualDataSet)
    }

    @Test
    fun `should return correct actual first work day`() {
        val activeDate = LocalDate.parse(ACTUAL_DATE)
        val firstWorkDate = LocalDate.parse(FIRST_WORK_DATE)
        val expectedDate = LocalDate.parse(EXPECTED_DATE)

        val scheduleSetup = TwoInTwoWorkScheduleSetup()

        val actualFirstDate = scheduleSetup.getActualFirstDate(
            activeDate = activeDate, firstWorkDate = firstWorkDate
        )

        Assertions.assertEquals(expectedDate, actualFirstDate)
    }

    companion object {
        private const val ACTUAL_DATE = "2022-01-27"
        private const val EXPECTED_DATE = "2022-01-30"
        private const val FIRST_WORK_DATE = "2022-01-14"
        private const val STEP = 4L
        private const val ONE_VALUE = 1L
        private const val RANGE_LENGTH = 80
        private const val RANGE_HALF_LENGTH = RANGE_LENGTH / 2L
        private const val ZERO = 0
    }
}
