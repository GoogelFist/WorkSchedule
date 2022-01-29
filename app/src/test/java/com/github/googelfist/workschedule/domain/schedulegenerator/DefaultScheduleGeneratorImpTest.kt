package com.github.googelfist.workschedule.domain.schedulegenerator

import com.github.googelfist.workschedule.InstantTaskExecutorExtension
import com.github.googelfist.workschedule.domain.schedulegenerator.datecontainer.DateContainer
import com.github.googelfist.workschedule.domain.schedulegenerator.daysgenerator.DaysGenerator
import com.github.googelfist.workschedule.domain.schedulegenerator.formatter.DateFormatter
import com.github.googelfist.workschedule.domain.schedulegenerator.models.Day
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.kotlin.mock
import java.time.LocalDate

internal class DefaultScheduleGeneratorImpTest {

    private val mockDaysGenerator = mock<DaysGenerator>()
    private val mockFormatter = mock<DateFormatter>()
    private val mockDateContainer = mock<DateContainer>()

    @AfterEach
    fun tearDown() {
        Mockito.reset(mockDaysGenerator)
        Mockito.reset(mockFormatter)
        Mockito.reset(mockDateContainer)
    }

    @Test
    @ExtendWith(InstantTaskExecutorExtension::class)
    fun `should return correct Live data when generate current schedule`() {
        val dayList = listOf<Day>()
        val date = LocalDate.parse(ACTUAL_DATE)
        val nowDate = LocalDate.parse(NOW_DATE)

        Mockito.`when`(mockDateContainer.getDateNow()).thenReturn(nowDate)

        Mockito.`when`(mockDaysGenerator.generateMonthDays(date)).thenReturn(dayList)

        val defaultScheduleGenerator = DefaultScheduleGeneratorImp(
            daysGenerator = mockDaysGenerator,
            formatter = mockFormatter,
            dateContainer = mockDateContainer
        )

        val expectedDayList: List<Day> = dayList
        val actualDayList = defaultScheduleGenerator.generateCurrentSchedule().value

        Mockito.verify(mockDaysGenerator).generateMonthDays(date)
        Mockito.verify(mockFormatter).format(date)

        Assertions.assertEquals(expectedDayList, actualDayList)
    }

    @Test
    @ExtendWith(InstantTaskExecutorExtension::class)
    fun `should return correct Live data when generate next schedule`() {
        val dayList = listOf<Day>()
        val date = LocalDate.parse(ACTUAL_DATE).plusMonths(ONE_VALUE)
        val nowDate = LocalDate.parse(NOW_DATE)

        Mockito.`when`(mockDateContainer.getDateNow()).thenReturn(nowDate)

        Mockito.`when`(mockDaysGenerator.generateMonthDays(date)).thenReturn(dayList)

        val defaultScheduleGenerator = DefaultScheduleGeneratorImp(
            daysGenerator = mockDaysGenerator,
            formatter = mockFormatter,
            dateContainer = mockDateContainer
        )

        val expectedDayList: List<Day> = dayList
        val actualDayList = defaultScheduleGenerator.generateNextSchedule().value

        Mockito.verify(mockDaysGenerator).generateMonthDays(date)
        Mockito.verify(mockFormatter).format(date)

        Assertions.assertEquals(expectedDayList, actualDayList)
    }

    @Test
    @ExtendWith(InstantTaskExecutorExtension::class)
    fun `should return correct Live data when generate previous schedule`() {
        val dayList = listOf<Day>()
        val date = LocalDate.parse(ACTUAL_DATE).minusMonths(ONE_VALUE)
        val nowDate = LocalDate.parse(NOW_DATE)

        Mockito.`when`(mockDateContainer.getDateNow()).thenReturn(nowDate)

        Mockito.`when`(mockDaysGenerator.generateMonthDays(date)).thenReturn(dayList)

        val defaultScheduleGenerator = DefaultScheduleGeneratorImp(
            daysGenerator = mockDaysGenerator,
            formatter = mockFormatter,
            dateContainer = mockDateContainer
        )

        val expectedDayList: List<Day> = dayList
        val actualDayList = defaultScheduleGenerator.generatePreviousSchedule().value

        Mockito.verify(mockDaysGenerator).generateMonthDays(date)
        Mockito.verify(mockFormatter).format(date)

        Assertions.assertEquals(expectedDayList, actualDayList)
    }

    @Test
    @ExtendWith(InstantTaskExecutorExtension::class)
    fun `should return correct Live data when date formatted`() {
        val date = LocalDate.parse(ACTUAL_DATE)
        val dayList = listOf<Day>()
        val nowDate = LocalDate.parse(NOW_DATE)

        Mockito.`when`(mockDateContainer.getDateNow()).thenReturn(nowDate)

        Mockito.`when`(mockFormatter.format(date)).thenReturn(EXPECTED_FORMATTED_STRING)
        Mockito.`when`(mockDaysGenerator.generateMonthDays(date)).thenReturn(dayList)

        val defaultScheduleGenerator = DefaultScheduleGeneratorImp(
            daysGenerator = mockDaysGenerator,
            formatter = mockFormatter,
            dateContainer = mockDateContainer
        )

        defaultScheduleGenerator.generateCurrentSchedule()
        val actualActiveFormatDate = defaultScheduleGenerator.getActiveFormatDate().value

        Assertions.assertEquals(EXPECTED_FORMATTED_STRING, actualActiveFormatDate)
    }

    companion object {
        private const val ACTUAL_DATE = "2022-01-27"

        private const val EXPECTED_FORMATTED_STRING = "January 2022"

        private const val ONE_VALUE = 1L

        private const val NOW_DATE = "2022-01-27"
    }
}
