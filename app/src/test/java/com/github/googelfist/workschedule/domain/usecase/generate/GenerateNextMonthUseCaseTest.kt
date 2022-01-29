package com.github.googelfist.workschedule.domain.usecase.generate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.googelfist.workschedule.domain.ScheduleGenerator
import com.github.googelfist.workschedule.domain.schedulegenerator.models.Day
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

internal class GenerateNextMonthUseCaseTest {

    private val mockGenerator = mock<ScheduleGenerator>()

    @AfterEach
    fun tearDown() {
        Mockito.reset(mockGenerator)
    }

    @Test
    fun `should return correct live data when generate next month`() {
        val dayList = listOf<Day>()
        val expectedLD: LiveData<List<Day>> = MutableLiveData(dayList)
        Mockito.`when`(mockGenerator.generateNextSchedule()).thenReturn(expectedLD)

        val generateNextMonthUseCase = GenerateNextMonthUseCase(mockGenerator)
        val actualLD = generateNextMonthUseCase()

        Mockito.verify(mockGenerator).generateNextSchedule()

        Assertions.assertEquals(expectedLD, actualLD)
    }
}
