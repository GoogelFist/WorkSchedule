package com.github.googelfist.workschedule.domain

import androidx.lifecycle.LiveData
import com.github.googelfist.workschedule.domain.models.days.Day
import java.time.LocalDate

interface ScheduleGenerator {

    fun generateWorkSchedule(activeDate: LocalDate, firstWorkDate: LocalDate): LiveData<List<Day>>

    fun generateDefaultSchedule(activeDate: LocalDate): LiveData<List<Day>>
}
