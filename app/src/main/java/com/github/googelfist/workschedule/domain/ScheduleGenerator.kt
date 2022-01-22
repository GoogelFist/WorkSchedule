package com.github.googelfist.workschedule.domain

import androidx.lifecycle.LiveData
import com.github.googelfist.workschedule.domain.models.days.Day

interface ScheduleGenerator {

    fun generateCurrentSchedule(): LiveData<List<Day>>

    fun generateNextSchedule(): LiveData<List<Day>>

    fun generatePreviousSchedule(): LiveData<List<Day>>

    fun getActiveFormatDate(): LiveData<String>
}
