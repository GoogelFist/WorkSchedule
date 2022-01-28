package com.github.googelfist.workschedule.domain.usecase.generate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.models.Day
import com.github.googelfist.workschedule.domain.ScheduleRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

internal class GenerateNextMonthUseCaseTest {

    private val mockRepository = mock<ScheduleRepository>()

    @AfterEach
    fun tearDown() {
        Mockito.reset(mockRepository)
    }

    @Test
    fun `should return correct live data when generate next month`() {
        val dayList = listOf<Day>()
        val expectedLD: LiveData<List<Day>> = MutableLiveData(dayList)
        Mockito.`when`(mockRepository.generateNextSchedule()).thenReturn(expectedLD)

        val generateNextMonthUseCase = GenerateNextMonthUseCase(mockRepository)
        val actualLD = generateNextMonthUseCase()

        Mockito.verify(mockRepository).generateNextSchedule()

        Assertions.assertEquals(expectedLD, actualLD)
    }
}
