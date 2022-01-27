package com.github.googelfist.workschedule.data.scheduledatasource

import androidx.lifecycle.LiveData
import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.models.Day

interface ScheduleGenerator {

    fun generateCurrentSchedule(): LiveData<List<Day>>

    fun generateNextSchedule(): LiveData<List<Day>>

    fun generatePreviousSchedule(): LiveData<List<Day>>

    fun getActiveFormatDate(): LiveData<String>
}