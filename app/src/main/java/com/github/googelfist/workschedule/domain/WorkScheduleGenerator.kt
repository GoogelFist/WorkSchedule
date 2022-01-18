package com.github.googelfist.workschedule.domain

import androidx.lifecycle.LiveData
import com.github.googelfist.workschedule.domain.models.days.Day
import java.time.LocalDate

interface WorkScheduleGenerator {

    fun generateWorkCurrentSchedule(
        firstWorkDate: LocalDate
    ): LiveData<List<Day>>

    fun generateWorkNexSchedule(
        firstWorkDate: LocalDate
    ): LiveData<List<Day>>

    fun generateWorkPreviousSchedule(
        firstWorkDate: LocalDate
    ): LiveData<List<Day>>

    fun getActiveFormatDate(): LiveData<String>
}
