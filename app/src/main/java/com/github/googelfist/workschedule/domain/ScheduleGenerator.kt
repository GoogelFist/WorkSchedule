package com.github.googelfist.workschedule.domain

import androidx.lifecycle.LiveData
import com.github.googelfist.workschedule.domain.models.days.Day
import java.time.LocalDate

interface ScheduleGenerator {
//    fun generateScheduleWorkDays(
//        date: LocalDate,
//        firstWorkDate: LocalDate,
//        step: Int
//    ): LiveData<List<Day>>

    fun generateSchedule(date: LocalDate, firstWorkDate: LocalDate): LiveData<List<Day>>

    fun getActualFirstDate(date: LocalDate, firstWorkDate: LocalDate): LocalDate
}
