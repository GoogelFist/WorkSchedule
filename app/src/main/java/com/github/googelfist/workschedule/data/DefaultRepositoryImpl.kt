package com.github.googelfist.workschedule.data

import androidx.lifecycle.LiveData
import com.github.googelfist.workschedule.data.preferencedatasource.PreferenceDataSource
import com.github.googelfist.workschedule.data.preferencedatasource.model.PreferencesData
import com.github.googelfist.workschedule.data.scheduledatasource.ScheduleGenerator
import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.models.Day
import com.github.googelfist.workschedule.domain.ScheduleRepository
import javax.inject.Inject

class DefaultRepositoryImpl @Inject constructor(
    private val scheduleGenerator: ScheduleGenerator,
    private val preferenceDataSource: PreferenceDataSource
) : ScheduleRepository {
    override fun loadPreference(): PreferencesData {
        return preferenceDataSource.generateDefaultPreference()
    }

    override fun getActiveFormatDate(): LiveData<String> {
        return scheduleGenerator.getActiveFormatDate()
    }

    override fun generateCurrentSchedule(): LiveData<List<Day>> {
        return scheduleGenerator.generateCurrentSchedule()
    }

    override fun generateNextSchedule(): LiveData<List<Day>> {
        return scheduleGenerator.generateNextSchedule()
    }

    override fun generatePreviousSchedule(): LiveData<List<Day>> {
        return scheduleGenerator.generatePreviousSchedule()
    }
}
