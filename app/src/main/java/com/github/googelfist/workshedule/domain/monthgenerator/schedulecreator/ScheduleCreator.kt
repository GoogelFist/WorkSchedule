package com.github.googelfist.workshedule.domain.monthgenerator.schedulecreator

import com.github.googelfist.workshedule.domain.models.ScheduleTypeState
import java.time.LocalDate

interface ScheduleCreator {
    suspend fun createWorkSchedule(scheduleType: ScheduleTypeState, date: LocalDate): Set<LocalDate>
}