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

internal class GeneratePreviousMonthUseCaseTest {

    private val mockGenerator = mock<ScheduleGenerator>()

    @AfterEach
    fun tearDown() {
        Mockito.reset(mockGenerator)
    }

    @Test
    fun `should return correct live data when generate previous month`() {
        val dayList = listOf<Day>()
        val expectedLD: LiveData<List<Day>> = MutableLiveData(dayList)
        Mockito.`when`(mockGenerator.generatePreviousSchedule()).thenReturn(expectedLD)

        val generatePreviousMonthUseCase = GeneratePreviousMonthUseCase(mockGenerator)
        val actualLD = generatePreviousMonthUseCase()

        Mockito.verify(mockGenerator).generatePreviousSchedule()

        Assertions.assertEquals(expectedLD, actualLD)
    }
}
