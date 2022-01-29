package com.github.googelfist.workschedule.domain.schedulegenerator.fabric

import com.github.googelfist.workschedule.domain.schedulegenerator.datecontainer.DateContainer
import com.github.googelfist.workschedule.domain.schedulegenerator.models.workday.WorkActiveDay
import com.github.googelfist.workschedule.domain.schedulegenerator.models.workday.WorkInActiveDay
import com.github.googelfist.workschedule.domain.schedulegenerator.models.workday.WorkToday
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import java.time.LocalDate

internal class WorkDaysFabricImplTest {

    private val mockDateContainer = mock<DateContainer>()

    @AfterEach
    fun tearDown() {
        Mockito.reset(mockDateContainer)
    }

    @Test
    fun `should return work inactive schedule day`() {
        val dateInMonth = LocalDate.parse(INACTIVE_WORK_DATE)
        val activeDay = LocalDate.parse(CURRENT_DATE)
        val workSchedule = mutableSetOf<LocalDate>(dateInMonth)
        val firstWorkDay = LocalDate.parse(FIRST_WORK_DAY)
        val actualFirstWorkDay = LocalDate.parse(ACTUAL_FIRST_WORK_DAY)
        val nowDate = LocalDate.parse(NOW_DATE)

        Mockito.`when`(mockDateContainer.getDateNow()).thenReturn(nowDate)

        val workDaysFabric = WorkDaysFabricImpl(mockDateContainer)

        val expectedDay = WorkInActiveDay(
            day = INACTIVE_WORK_DAY_VALUE,
            month = INACTIVE_WORK_MONTH_VALUE,
            year = INACTIVE_WORK_YEAR_VALUE,
            isWork = true,
            isWeekend = false
        )

        val actualDay = workDaysFabric.getWorkDay(
            dateInMonth = dateInMonth,
            activeDate = activeDay,
            firstWorkDay = firstWorkDay,
            actualFirstWorkDay = actualFirstWorkDay,
            workSchedule = workSchedule
        )

        Assertions.assertEquals(expectedDay, actualDay)
    }

    @Test
    fun `should return weekend inactive schedule day`() {
        val dateInMonth = LocalDate.parse(INACTIVE_WEEKEND_DATE)
        val activeDay = LocalDate.parse(CURRENT_DATE)
        val workSchedule = mutableSetOf<LocalDate>()
        val firstWorkDay = LocalDate.parse(FIRST_WORK_DAY)
        val actualFirstWorkDay = LocalDate.parse(ACTUAL_FIRST_WORK_DAY)
        val nowDate = LocalDate.parse(NOW_DATE)

        Mockito.`when`(mockDateContainer.getDateNow()).thenReturn(nowDate)

        val workDaysFabric = WorkDaysFabricImpl(mockDateContainer)

        val expectedDay = WorkInActiveDay(
            day = INACTIVE_WEEKEND_DAY_VALUE,
            month = INACTIVE_WEEKEND_MONTH_VALUE,
            year = INACTIVE_WEEKEND_YEAR_VALUE,
            isWork = false,
            isWeekend = true
        )

        val actualDay = workDaysFabric.getWorkDay(
            dateInMonth = dateInMonth,
            activeDate = activeDay,
            firstWorkDay = firstWorkDay,
            actualFirstWorkDay = actualFirstWorkDay,
            workSchedule = workSchedule
        )

        Assertions.assertEquals(expectedDay, actualDay)
    }

    @Test
    fun `should return work today schedule day`() {
        val dateInMonth = LocalDate.parse(TODAY_DATE)
        val activeDay = LocalDate.parse(CURRENT_DATE)
        val workSchedule = mutableSetOf<LocalDate>(dateInMonth)
        val firstWorkDay = LocalDate.parse(FIRST_WORK_DAY)
        val actualFirstWorkDay = LocalDate.parse(ACTUAL_FIRST_WORK_DAY)
        val nowDate = LocalDate.parse(NOW_DATE)

        Mockito.`when`(mockDateContainer.getDateNow()).thenReturn(nowDate)

        val workDaysFabric = WorkDaysFabricImpl(mockDateContainer)

        val expectedDay = WorkToday(
            day = TODAY_WORK_DAY_VALUE,
            month = TODAY_WORK_MONTH_VALUE,
            year = TODAY_WORK_YEAR_VALUE,
            isWork = true,
            isWeekend = false
        )

        val actualDay = workDaysFabric.getWorkDay(
            dateInMonth = dateInMonth,
            activeDate = activeDay,
            firstWorkDay = firstWorkDay,
            actualFirstWorkDay = actualFirstWorkDay,
            workSchedule = workSchedule
        )

        Assertions.assertEquals(expectedDay, actualDay)
    }

    @Test
    fun `should return weekend today schedule day`() {
        val dateInMonth = LocalDate.parse(TODAY_DATE)
        val activeDay = LocalDate.parse(CURRENT_DATE)
        val workSchedule = mutableSetOf<LocalDate>()
        val firstWorkDay = LocalDate.parse(FIRST_WORK_DAY)
        val actualFirstWorkDay = LocalDate.parse(ACTUAL_FIRST_WORK_DAY)
        val nowDate = LocalDate.parse(NOW_DATE)

        Mockito.`when`(mockDateContainer.getDateNow()).thenReturn(nowDate)

        val workDaysFabric = WorkDaysFabricImpl(mockDateContainer)

        val expectedDay = WorkToday(
            day = TODAY_WEEKEND_DAY_VALUE,
            month = TODAY_WEEKEND_MONTH_VALUE,
            year = TODAY_WEEKEND_YEAR_VALUE,
            isWork = false,
            isWeekend = true
        )

        val actualDay = workDaysFabric.getWorkDay(
            dateInMonth = dateInMonth,
            activeDate = activeDay,
            firstWorkDay = firstWorkDay,
            actualFirstWorkDay = actualFirstWorkDay,
            workSchedule = workSchedule
        )

        Assertions.assertEquals(expectedDay, actualDay)
    }

    @Test
    fun `should return work active schedule day`() {
        val dateInMonth = LocalDate.parse(ACTIVE_WORK_DATE)
        val activeDay = LocalDate.parse(CURRENT_DATE)
        val workSchedule = mutableSetOf<LocalDate>(dateInMonth)
        val firstWorkDay = LocalDate.parse(FIRST_WORK_DAY)
        val actualFirstWorkDay = LocalDate.parse(ACTUAL_FIRST_WORK_DAY)
        val nowDate = LocalDate.parse(NOW_DATE)

        Mockito.`when`(mockDateContainer.getDateNow()).thenReturn(nowDate)

        val workDaysFabric = WorkDaysFabricImpl(mockDateContainer)

        val expectedDay = WorkActiveDay(
            day = ACTIVE_WORK_DAY_VALUE,
            month = ACTIVE_WORK_MONTH_VALUE,
            year = ACTIVE_WORK_YEAR_VALUE,
            isWork = true,
            isWeekend = false
        )

        val actualDay = workDaysFabric.getWorkDay(
            dateInMonth = dateInMonth,
            activeDate = activeDay,
            firstWorkDay = firstWorkDay,
            actualFirstWorkDay = actualFirstWorkDay,
            workSchedule = workSchedule
        )

        Assertions.assertEquals(expectedDay, actualDay)
    }

    @Test
    fun `should return weekend active schedule day`() {
        val dateInMonth = LocalDate.parse(ACTIVE_WEEKEND_DATE)
        val activeDay = LocalDate.parse(CURRENT_DATE)
        val workSchedule = mutableSetOf<LocalDate>()
        val firstWorkDay = LocalDate.parse(FIRST_WORK_DAY)
        val actualFirstWorkDay = LocalDate.parse(ACTUAL_FIRST_WORK_DAY)
        val nowDate = LocalDate.parse(NOW_DATE)

        Mockito.`when`(mockDateContainer.getDateNow()).thenReturn(nowDate)

        val workDaysFabric = WorkDaysFabricImpl(mockDateContainer)

        val expectedDay = WorkActiveDay(
            day = ACTIVE_WEEKEND_DAY_VALUE,
            month = ACTIVE_WEEKEND_MONTH_VALUE,
            year = ACTIVE_WEEKEND_YEAR_VALUE,
            isWork = false,
            isWeekend = true
        )

        val actualDay = workDaysFabric.getWorkDay(
            dateInMonth = dateInMonth,
            activeDate = activeDay,
            firstWorkDay = firstWorkDay,
            actualFirstWorkDay = actualFirstWorkDay,
            workSchedule = workSchedule
        )

        Assertions.assertEquals(expectedDay, actualDay)
    }

    companion object {
        private const val INACTIVE_WORK_DAY_VALUE = 30
        private const val INACTIVE_WORK_MONTH_VALUE = 12
        private const val INACTIVE_WORK_YEAR_VALUE = 2021
        private const val INACTIVE_WORK_DATE = "2021-12-30"

        private const val INACTIVE_WEEKEND_DAY_VALUE = 28
        private const val INACTIVE_WEEKEND_MONTH_VALUE = 12
        private const val INACTIVE_WEEKEND_YEAR_VALUE = 2021
        private const val INACTIVE_WEEKEND_DATE = "2021-12-28"

        private const val TODAY_WORK_DAY_VALUE = 27
        private const val TODAY_WORK_MONTH_VALUE = 1
        private const val TODAY_WORK_YEAR_VALUE = 2022

        private const val TODAY_DATE = "2022-01-27"

        private const val TODAY_WEEKEND_DAY_VALUE = 27
        private const val TODAY_WEEKEND_MONTH_VALUE = 1
        private const val TODAY_WEEKEND_YEAR_VALUE = 2022

        private const val ACTIVE_WORK_DAY_VALUE = 15
        private const val ACTIVE_WORK_MONTH_VALUE = 1
        private const val ACTIVE_WORK_YEAR_VALUE = 2022
        private const val ACTIVE_WORK_DATE = "2022-01-15"

        private const val ACTIVE_WEEKEND_DAY_VALUE = 16
        private const val ACTIVE_WEEKEND_MONTH_VALUE = 1
        private const val ACTIVE_WEEKEND_YEAR_VALUE = 2022
        private const val ACTIVE_WEEKEND_DATE = "2022-01-16"

        private const val CURRENT_DATE = "2022-01-27"
        private const val FIRST_WORK_DAY = "2021-12-26"
        private const val ACTUAL_FIRST_WORK_DAY = "2021-12-29"
        private const val NOW_DATE = "2022-01-27"
    }
}
