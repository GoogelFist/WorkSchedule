package com.github.googelfist.workschedule.domain.schedulegenerator.daysgenerator.fabric

import com.github.googelfist.workschedule.domain.schedulegenerator.datecontainer.DateContainer
import com.github.googelfist.workschedule.domain.schedulegenerator.models.DefaultDay
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import java.time.LocalDate

internal class DefaultDaysFabricImplTest {

    private val mockDateContainer = mock<DateContainer>()

    @AfterEach
    fun tearDown() {
        Mockito.reset(mockDateContainer)
    }

    @Test
    fun `should return default inactive day`() {
        val dateInMonth = LocalDate.parse(INACTIVE_DATE)
        val activeDay = LocalDate.parse(CURRENT_DATE)
        val nowDate = LocalDate.parse(NOW_DATE)

        Mockito.`when`(mockDateContainer.getDateNow()).thenReturn(nowDate)

        val defaultDaysFabric = DefaultDaysFabricImpl(mockDateContainer)

        val expectedDay =
            DefaultDay(
                day = INACTIVE_DAY_VALUE,
                month = INACTIVE_MONTH_VALUE,
                year = INACTIVE_YEAR_VALUE,
                today = false,
                currentMonth = false
            )
        val actualDay = defaultDaysFabric.getDay(dateInMonth, activeDay)

        Assertions.assertEquals(expectedDay, actualDay)
    }

    @Test
    fun `should return default today day`() {
        val dateInMonth = LocalDate.parse(TODAY_DATE)
        val activeDay = LocalDate.parse(CURRENT_DATE)
        val nowDate = LocalDate.parse(NOW_DATE)

        Mockito.`when`(mockDateContainer.getDateNow()).thenReturn(nowDate)

        val defaultDaysFabric = DefaultDaysFabricImpl(mockDateContainer)

        val expectedDay = DefaultDay(
            day = TODAY_DAY_VALUE,
            month = TODAY_MONTH_VALUE,
            year = TODAY_YEAR_VALUE,
            today = true,
            currentMonth = true
        )
        val actualDay = defaultDaysFabric.getDay(dateInMonth, activeDay)

        Assertions.assertEquals(expectedDay, actualDay)
    }

    @Test
    fun `should return default active day`() {
        val dateInMonth = LocalDate.parse(ACTIVE_DATE)
        val activeDay = LocalDate.parse(CURRENT_DATE)
        val nowDate = LocalDate.parse(NOW_DATE)

        Mockito.`when`(mockDateContainer.getDateNow()).thenReturn(nowDate)

        val defaultDaysFabric = DefaultDaysFabricImpl(mockDateContainer)

        val expectedDay = DefaultDay(
            day = ACTIVE_DAY_VALUE,
            month = ACTIVE_MONTH_VALUE,
            year = ACTIVE_YEAR_VALUE,
            today = false,
            currentMonth = true
        )
        val actualDay = defaultDaysFabric.getDay(dateInMonth, activeDay)

        Assertions.assertEquals(expectedDay, actualDay)
    }

    companion object {
        private const val INACTIVE_DAY_VALUE = 30
        private const val INACTIVE_MONTH_VALUE = 12
        private const val INACTIVE_YEAR_VALUE = 2021

        private const val TODAY_DAY_VALUE = 27
        private const val TODAY_MONTH_VALUE = 1
        private const val TODAY_YEAR_VALUE = 2022

        private const val ACTIVE_DAY_VALUE = 15
        private const val ACTIVE_MONTH_VALUE = 1
        private const val ACTIVE_YEAR_VALUE = 2022

        private const val INACTIVE_DATE = "2021-12-30"
        private const val ACTIVE_DATE = "2022-01-15"
        private const val TODAY_DATE = "2022-01-27"
        private const val CURRENT_DATE = "2022-01-27"
        private const val NOW_DATE = "2022-01-27"
    }
}
