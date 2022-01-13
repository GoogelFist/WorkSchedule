package com.github.googelfist.workschedule.domain

import androidx.lifecycle.LiveData
import com.github.googelfist.workschedule.domain.models.days.Day
import java.time.LocalDate

interface SchedulesGenerator {
    fun generateScheduleWorkDays(
        date: LocalDate,
        firstWorkDate: LocalDate,
        step: Int
    ): LiveData<List<Day>>

    fun generateScheduleDays(date: LocalDate): LiveData<List<Day>>
}
