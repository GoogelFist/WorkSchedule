package com.github.googelfist.workschedule.data

import com.github.googelfist.workschedule.InstantTaskExecutorExtension
import com.github.googelfist.workschedule.data.preferencedatasource.PreferenceDataSource
import com.github.googelfist.workschedule.data.preferencedatasource.model.PreferencesData
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.kotlin.mock

internal class PreferenceRepositoryImplTest {

    private val mockPreferenceDataSource = mock<PreferenceDataSource>()

    @AfterEach
    fun tearDown() {
        Mockito.reset(mockPreferenceDataSource)
    }

    @Test
    @ExtendWith(InstantTaskExecutorExtension::class)
    fun `should return correct preference`() {
        val expectedPreference = PreferencesData(
            scheduleType = SCHEDULE_TYPE,
            firstWorkDate = FIRST_WORK_DAY
        )

        Mockito.`when`(mockPreferenceDataSource.loadPreference())
            .thenReturn(expectedPreference)

        val preferenceRepository = PreferenceRepositoryImpl(
            preferenceDataSource = mockPreferenceDataSource
        )

        val actualPreference = preferenceRepository.loadPreference()

        Mockito.verify(mockPreferenceDataSource).loadPreference()

        Assertions.assertEquals(expectedPreference, actualPreference)
    }

    companion object {
        private const val SCHEDULE_TYPE = "2 / 2"
        private const val FIRST_WORK_DAY = "2022-01-01"
    }
}
