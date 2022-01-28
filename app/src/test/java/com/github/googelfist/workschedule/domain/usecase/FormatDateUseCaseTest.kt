package com.github.googelfist.workschedule.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.googelfist.workschedule.domain.ScheduleRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

internal class FormatDateUseCaseTest {

    private val mockRepository = mock<ScheduleRepository>()

    @AfterEach
    fun tearDown() {
        Mockito.reset(mockRepository)
    }

    @Test
    fun `should return correct live data when date was format`() {
        val expectedLD: LiveData<String> = MutableLiveData(EXPECTED_STRING)
        Mockito.`when`(mockRepository.getActiveFormatDate()).thenReturn(expectedLD)

        val formatDateUseCase = FormatDateUseCase(mockRepository)
        val actualLD = formatDateUseCase()

        Mockito.verify(mockRepository).getActiveFormatDate()

        Assertions.assertEquals(expectedLD, actualLD)
    }

    companion object {
        private const val EXPECTED_STRING = "January 2022"
    }
}
