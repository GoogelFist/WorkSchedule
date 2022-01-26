package com.github.googelfist.workschedule.domain

import androidx.lifecycle.LiveData
import com.github.googelfist.workschedule.data.preferencedatasource.model.PreferencesData
import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.models.Day

interface ScheduleRepository {

    fun loadPreference(): PreferencesData

    fun getActiveFormatDate(): LiveData<String>

    fun generateCurrentSchedule(): LiveData<List<Day>>

    fun generateNextSchedule(): LiveData<List<Day>>

    fun generatePreviousSchedule(): LiveData<List<Day>>
}
