package com.github.googelfist.workshedule.domain.daysgenerator

import com.github.googelfist.workshedule.domain.models.day.Day
import com.github.googelfist.workshedule.domain.models.day.DefaultDay
import com.github.googelfist.workshedule.domain.monthgenerator.DateNowContainer
import com.github.googelfist.workshedule.domain.monthgenerator.def.daysgenerator.DefaultDaysGeneratorImpl
import com.github.googelfist.workshedule.domain.monthgenerator.def.fabric.DefaultDaysFabricImpl
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import java.time.LocalDate

internal class daysGeneratorImplTest {

    private val mockDateNowContainer = mock<DateNowContainer>()
    private lateinit var expectedDayList: List<Day>

    @BeforeEach
    fun setUp() {
        expectedDayList = listOf<Day>(
            DefaultDay(day = 28, month = 3, year = 2022, today = false, currentMonth = false),
            DefaultDay(day = 29, month = 3, year = 2022, today = false, currentMonth = false),
            DefaultDay(day = 30, month = 3, year = 2022, today = false, currentMonth = false),
            DefaultDay(day = 31, month = 3, year = 2022, today = false, currentMonth = false),
            DefaultDay(day = 1, month = 4, year = 2022, today = false, currentMonth = true),
            DefaultDay(day = 2, month = 4, year = 2022, today = false, currentMonth = true),
            DefaultDay(day = 3, month = 4, year = 2022, today = false, currentMonth = true),
            DefaultDay(day = 4, month = 4, year = 2022, today = true, currentMonth = true),
            DefaultDay(day = 5, month = 4, year = 2022, today = false, currentMonth = true),
            DefaultDay(day = 6, month = 4, year = 2022, today = false, currentMonth = true),
            DefaultDay(day = 7, month = 4, year = 2022, today = false, currentMonth = true),
            DefaultDay(day = 8, month = 4, year = 2022, today = false, currentMonth = true),
            DefaultDay(day = 9, month = 4, year = 2022, today = false, currentMonth = true),
            DefaultDay(day = 10, month = 4, year = 2022, today = false, currentMonth = true),
            DefaultDay(day = 11, month = 4, year = 2022, today = false, currentMonth = true),
            DefaultDay(day = 12, month = 4, year = 2022, today = false, currentMonth = true),
            DefaultDay(day = 13, month = 4, year = 2022, today = false, currentMonth = true),
            DefaultDay(day = 14, month = 4, year = 2022, today = false, currentMonth = true),
            DefaultDay(day = 15, month = 4, year = 2022, today = false, currentMonth = true),
            DefaultDay(day = 16, month = 4, year = 2022, today = false, currentMonth = true),
            DefaultDay(day = 17, month = 4, year = 2022, today = false, currentMonth = true),
            DefaultDay(day = 18, month = 4, year = 2022, today = false, currentMonth = true),
            DefaultDay(day = 19, month = 4, year = 2022, today = false, currentMonth = true),
            DefaultDay(day = 20, month = 4, year = 2022, today = false, currentMonth = true),
            DefaultDay(day = 21, month = 4, year = 2022, today = false, currentMonth = true),
            DefaultDay(day = 22, month = 4, year = 2022, today = false, currentMonth = true),
            DefaultDay(day = 23, month = 4, year = 2022, today = false, currentMonth = true),
            DefaultDay(day = 24, month = 4, year = 2022, today = false, currentMonth = true),
            DefaultDay(day = 25, month = 4, year = 2022, today = false, currentMonth = true),
            DefaultDay(day = 26, month = 4, year = 2022, today = false, currentMonth = true),
            DefaultDay(day = 27, month = 4, year = 2022, today = false, currentMonth = true),
            DefaultDay(day = 28, month = 4, year = 2022, today = false, currentMonth = true),
            DefaultDay(day = 29, month = 4, year = 2022, today = false, currentMonth = true),
            DefaultDay(day = 30, month = 4, year = 2022, today = false, currentMonth = true),
            DefaultDay(day = 1, month = 5, year = 2022, today = false, currentMonth = false),
            DefaultDay(day = 2, month = 5, year = 2022, today = false, currentMonth = false),
            DefaultDay(day = 3, month = 5, year = 2022, today = false, currentMonth = false),
            DefaultDay(day = 4, month = 5, year = 2022, today = false, currentMonth = false),
            DefaultDay(day = 5, month = 5, year = 2022, today = false, currentMonth = false),
            DefaultDay(day = 6, month = 5, year = 2022, today = false, currentMonth = false),
            DefaultDay(day = 7, month = 5, year = 2022, today = false, currentMonth = false),
            DefaultDay(day = 8, month = 5, year = 2022, today = false, currentMonth = false)
        )
    }

    @AfterEach
    fun tearDown() {
        Mockito.reset(mockDateNowContainer)
    }

    @Test
    fun `should generate correct default day list`() {
        val defaultDaysFabric = DefaultDaysFabricImpl(mockDateNowContainer)
        val daysGenerator = DefaultDaysGeneratorImpl(defaultDaysFabric)
        val date = LocalDate.parse(TODAY_DATE)

        Mockito.`when`(mockDateNowContainer.getDate()).thenReturn(date)

        val actualDayList = daysGenerator.generateDays(date)

        Assertions.assertEquals(expectedDayList, actualDayList)
    }

    companion object {
        private const val TODAY_DATE = "2022-04-04"
    }
}