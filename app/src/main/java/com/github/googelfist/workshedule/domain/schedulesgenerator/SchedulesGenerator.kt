package com.github.googelfist.workshedule.domain.schedulesgenerator

import androidx.lifecycle.LiveData
import com.github.googelfist.workshedule.domain.models.Day
import java.time.LocalDate

interface SchedulesGenerator {
    fun generateScheduleWorkDays(date: LocalDate, firstWorkDate: LocalDate, step: Int): LiveData<List<Day>>

    fun generateScheduleDays(date: LocalDate): LiveData<List<Day>>
}