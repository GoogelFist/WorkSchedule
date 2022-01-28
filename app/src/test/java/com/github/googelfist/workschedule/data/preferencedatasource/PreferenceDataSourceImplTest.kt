package com.github.googelfist.workschedule.data.preferencedatasource

import android.app.Application
import android.content.SharedPreferences
import android.content.res.Resources
import com.github.googelfist.workschedule.R
import com.github.googelfist.workschedule.data.preferencedatasource.model.PreferencesData
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import org.mockito.kotlin.mock

internal class PreferenceDataSourceImplTest {

    private val mockApplication = mock<Application>()
    private val mockSharedPreferences = mock<SharedPreferences>()
    private val mockResources = mock<Resources>()
    private val mockSPEditor = mock<SharedPreferences.Editor>()

    @AfterEach
    fun tearDown() {
        Mockito.reset(mockApplication)
        Mockito.reset(mockSharedPreferences)
        Mockito.reset(mockResources)
        Mockito.reset(mockSPEditor)
    }

    @Test
    fun `should return correct preference data when preference exist`() {
        val expectedPreferenceModel = PreferencesData(
            scheduleType = SCHEDULE_TYPE_VALUE,
            firstWorkDate = FIRST_WORK_DATE_VALUE
        )

        Mockito.`when`(mockApplication.getSharedPreferences(anyString(), anyInt()))
            .thenReturn(mockSharedPreferences)

        Mockito.`when`(mockApplication.resources).thenReturn(mockResources)

        Mockito.`when`(mockResources.getString(R.string.dd_schedule_type_key))
            .thenReturn(SCHEDULE_TYPE_KEY)

        Mockito.`when`(mockResources.getString(R.string.p_date_picker_key))
            .thenReturn(FIRST_WORK_DATE_KEY)

        Mockito.`when`(
            mockSharedPreferences.getString(FIRST_WORK_DATE_KEY, FIRST_WORK_DATE_DEFAULT_VALUE)
        ).thenReturn(FIRST_WORK_DATE_VALUE)

        Mockito.`when`(
            mockSharedPreferences.getString(SCHEDULE_TYPE_KEY, SCHEDULE_TYPE_DEFAULT_VALUE)
        ).thenReturn(SCHEDULE_TYPE_VALUE)

        val preferenceDataSource = PreferenceDataSourceImpl(mockApplication)
        val actualPreferenceModel = preferenceDataSource.loadPreference()

        Assertions.assertEquals(expectedPreferenceModel, actualPreferenceModel)
    }

    @Test
    fun `should return correct preference data when preference no exist`() {
        val expectedPreferenceModel = PreferencesData(
            scheduleType = SCHEDULE_TYPE_DEFAULT_VALUE,
            firstWorkDate = FIRST_WORK_DATE_DEFAULT_VALUE
        )

        Mockito.`when`(mockApplication.getSharedPreferences(anyString(), anyInt()))
            .thenReturn(mockSharedPreferences)

        Mockito.`when`(mockApplication.resources).thenReturn(mockResources)

        Mockito.`when`(mockResources.getString(R.string.dd_schedule_type_key))
            .thenReturn(SCHEDULE_TYPE_DEFAULT_VALUE)

        Mockito.`when`(mockResources.getString(R.string.p_date_picker_key))
            .thenReturn(FIRST_WORK_DATE_DEFAULT_VALUE)

        Mockito.`when`(
            mockSharedPreferences.getString(FIRST_WORK_DATE_KEY, FIRST_WORK_DATE_DEFAULT_VALUE)
        ).thenReturn(FIRST_WORK_DATE_DEFAULT_VALUE)

        Mockito.`when`(
            mockSharedPreferences.getString(SCHEDULE_TYPE_KEY, SCHEDULE_TYPE_DEFAULT_VALUE)
        ).thenReturn(SCHEDULE_TYPE_DEFAULT_VALUE)

        val preferenceDataSource = PreferenceDataSourceImpl(mockApplication)
        val actualPreferenceModel = preferenceDataSource.loadPreference()

        Assertions.assertEquals(expectedPreferenceModel, actualPreferenceModel)
    }

    @Test
    fun `verifying saving preferences`() {
        val preferenceModel = PreferencesData(
            scheduleType = SCHEDULE_TYPE_VALUE,
            firstWorkDate = FIRST_WORK_DATE_VALUE
        )

        Mockito.`when`(mockApplication.getSharedPreferences(anyString(), anyInt()))
            .thenReturn(mockSharedPreferences)

        Mockito.`when`(mockApplication.resources).thenReturn(mockResources)

        Mockito.`when`(mockSharedPreferences.edit()).thenReturn(mockSPEditor)

        Mockito.`when`(mockSPEditor.putString(SCHEDULE_TYPE_KEY, SCHEDULE_TYPE_VALUE))
            .thenReturn(mockSPEditor)

        Mockito.`when`(mockSPEditor.putString(FIRST_WORK_DATE_KEY, FIRST_WORK_DATE_VALUE))
            .thenReturn(mockSPEditor)

        Mockito.`when`(mockResources.getString(R.string.dd_schedule_type_key))
            .thenReturn(SCHEDULE_TYPE_KEY)

        Mockito.`when`(mockResources.getString(R.string.p_date_picker_key))
            .thenReturn(FIRST_WORK_DATE_KEY)

        val preferenceDataSource = PreferenceDataSourceImpl(mockApplication)

        preferenceDataSource.savePreference(preferenceModel)

        Mockito.verify(
            mockSharedPreferences
                .edit()
                .putString(SCHEDULE_TYPE_KEY, SCHEDULE_TYPE_VALUE)
                .putString(FIRST_WORK_DATE_KEY, FIRST_WORK_DATE_VALUE)
        ).apply()
    }

    @Test
    fun `should return correct default preference data`() {
        val expectedPreferenceData = PreferencesData(
            scheduleType = SCHEDULE_TYPE_DEFAULT_VALUE,
            firstWorkDate = FIRST_WORK_DATE_DEFAULT_VALUE
        )

        Mockito.`when`(mockApplication.getSharedPreferences(anyString(), anyInt()))
            .thenReturn(mockSharedPreferences)

        val preferenceDataSource = PreferenceDataSourceImpl(mockApplication)

        val actualPreferenceData = preferenceDataSource.generateDefaultPreference()

        Assertions.assertEquals(expectedPreferenceData, actualPreferenceData)
    }

    companion object {
        private const val FIRST_WORK_DATE_DEFAULT_VALUE = "Choose start work date"
        private const val SCHEDULE_TYPE_DEFAULT_VALUE = "Choose the schedule"

        private const val SCHEDULE_TYPE_KEY = "schedule_type"
        private const val SCHEDULE_TYPE_VALUE = "2 / 2"

        private const val FIRST_WORK_DATE_KEY = "first_work_date"
        private const val FIRST_WORK_DATE_VALUE = "2022-01-26"
    }
}
