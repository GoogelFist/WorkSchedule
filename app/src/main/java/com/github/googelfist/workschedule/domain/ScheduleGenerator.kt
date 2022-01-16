package com.github.googelfist.workschedule.domain

import androidx.lifecycle.LiveData
import com.github.googelfist.workschedule.domain.models.days.Day
import java.time.LocalDate

interface ScheduleGenerator {

    fun generateSchedule(activeDate: LocalDate, firstWorkDate: LocalDate): LiveData<List<Day>>

    fun getActualFirstDate(activeDate: LocalDate, firstWorkDate: LocalDate): LocalDate
}
