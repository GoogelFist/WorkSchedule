package com.github.googelfist.workschedule.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.googelfist.workschedule.data.preferencedatasource.PreferenceDataSource
import com.github.googelfist.workschedule.data.preferencedatasource.model.PreferencesData
import com.github.googelfist.workschedule.data.scheduledatasource.ScheduleGenerator
import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.InstantTaskExecutorExtension
import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.models.Day
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.kotlin.mock

internal class DefaultRepositoryImplTest {

    private val mockScheduleGenerator = mock<ScheduleGenerator>()
    private val mockPreferenceDataSource = mock<PreferenceDataSource>()

    @AfterEach
    fun tearDown() {
        Mockito.reset(mockScheduleGenerator)
        Mockito.reset(mockPreferenceDataSource)
    }

    @Test
    fun `should return correct preference`() {
        val expectedPreference = PreferencesData(
            scheduleType = DEFAULT_SCHEDULE_TYPE,
            firstWorkDate = DEFAULT_FIRST_WORK_DAY
        )

        Mockito.`when`(mockPreferenceDataSource.generateDefaultPreference())
            .thenReturn(expectedPreference)

        val defaultRepository = DefaultRepositoryImpl(
            scheduleGenerator = mockScheduleGenerator,
            preferenceDataSource = mockPreferenceDataSource
        )

        val actualPreference = defaultRepository.loadPreference()

        Mockito.verify(mockPreferenceDataSource).generateDefaultPreference()

        Assertions.assertEquals(expectedPreference, actualPreference)
    }

    @Test
    @ExtendWith(InstantTaskExecutorExtension::class)
    fun `should return correct Live data when date was format`() {
        val expectedLD: LiveData<String> = MutableLiveData(EXPECTED_STRING)

        Mockito.`when`(mockScheduleGenerator.getActiveFormatDate()).thenReturn(expectedLD)

        val defaultRepository = DefaultRepositoryImpl(
            scheduleGenerator = mockScheduleGenerator,
            preferenceDataSource = mockPreferenceDataSource
        )

        val actualLD = defaultRepository.getActiveFormatDate()

        Mockito.verify(mockScheduleGenerator).getActiveFormatDate()

        Assertions.assertEquals(expectedLD, actualLD)
    }

    @Test
    @ExtendWith(InstantTaskExecutorExtension::class)
    fun `should return correct Live data when generate current schedule`() {
        val dayList = listOf<Day>()
        val expectedLD: LiveData<List<Day>> = MutableLiveData(dayList)

        Mockito.`when`(mockScheduleGenerator.generateCurrentSchedule()).thenReturn(expectedLD)

        val defaultRepository = DefaultRepositoryImpl(
            scheduleGenerator = mockScheduleGenerator,
            preferenceDataSource = mockPreferenceDataSource
        )

        val actualLD = defaultRepository.generateCurrentSchedule()

        Mockito.verify(mockScheduleGenerator).generateCurrentSchedule()

        Assertions.assertEquals(expectedLD, actualLD)
    }

    @Test
    @ExtendWith(InstantTaskExecutorExtension::class)
    fun `should return correct Live data when generate next schedule`() {
        val dayList = listOf<Day>()
        val expectedLD: LiveData<List<Day>> = MutableLiveData(dayList)

        Mockito.`when`(mockScheduleGenerator.generateNextSchedule()).thenReturn(expectedLD)

        val defaultRepository = DefaultRepositoryImpl(
            scheduleGenerator = mockScheduleGenerator,
            preferenceDataSource = mockPreferenceDataSource
        )

        val actualLD = defaultRepository.generateNextSchedule()

        Mockito.verify(mockScheduleGenerator).generateNextSchedule()

        Assertions.assertEquals(expectedLD, actualLD)
    }

    @Test
    @ExtendWith(InstantTaskExecutorExtension::class)
    fun `should return correct Live data when generate previous schedule`() {
        val dayList = listOf<Day>()
        val expectedLD: LiveData<List<Day>> = MutableLiveData(dayList)

        Mockito.`when`(mockScheduleGenerator.generatePreviousSchedule()).thenReturn(expectedLD)

        val defaultRepository = DefaultRepositoryImpl(
            scheduleGenerator = mockScheduleGenerator,
            preferenceDataSource = mockPreferenceDataSource
        )

        val actualLD = defaultRepository.generatePreviousSchedule()

        Mockito.verify(mockScheduleGenerator).generatePreviousSchedule()

        Assertions.assertEquals(expectedLD, actualLD)
    }

    companion object {
        private const val EXPECTED_STRING = "January 2022"

        private const val DEFAULT_SCHEDULE_TYPE = "Choose the schedule"
        private const val DEFAULT_FIRST_WORK_DAY = "Choose start work date"
    }
}
