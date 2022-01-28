package com.github.googelfist.workschedule.domain.usecase

import com.github.googelfist.workschedule.data.preferencedatasource.model.PreferencesData
import com.github.googelfist.workschedule.domain.PreferenceRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

internal class GetScheduleTypeUseCaseTest {

    private val mockRepository = mock<PreferenceRepository>()

    @AfterEach
    fun tearDown() {
        Mockito.reset(mockRepository)
    }

    @Test
    fun `should return correct schedule type`() {
        val preference = PreferencesData(
            scheduleType = SCHEDULE_TYPE,
            firstWorkDate = FIRST_WORK_DAY
        )
        Mockito.`when`(mockRepository.loadPreference()).thenReturn(preference)

        val formatDateUseCase = GetScheduleTypeUseCase(mockRepository)

        val expectedScheduleType = preference.scheduleType
        val actualScheduleType = formatDateUseCase()

        Mockito.verify(mockRepository).loadPreference()

        Assertions.assertEquals(expectedScheduleType, actualScheduleType)
    }

    companion object {
        private const val SCHEDULE_TYPE = "Choose the schedule"
        private const val FIRST_WORK_DAY = "Choose start work date"
    }
}
