package com.github.googelfist.workschedule.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.googelfist.workschedule.domain.ScheduleGenerator
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

internal class FormatDateUseCaseTest {

    private val mockGenerator = mock<ScheduleGenerator>()

    @AfterEach
    fun tearDown() {
        Mockito.reset(mockGenerator)
    }

    @Test
    fun `should return correct live data when date was format`() {
        val expectedLD: LiveData<String> = MutableLiveData(EXPECTED_STRING)
        Mockito.`when`(mockGenerator.getActiveFormatDate()).thenReturn(expectedLD)

        val formatDateUseCase = FormatDateUseCase(mockGenerator)
        val actualLD = formatDateUseCase()

        Mockito.verify(mockGenerator).getActiveFormatDate()

        Assertions.assertEquals(expectedLD, actualLD)
    }

    companion object {
        private const val EXPECTED_STRING = "January 2022"
    }
}
