package com.github.googelfist.workshedule.domain.monthgenerator.schedulecreator

import java.time.LocalDate

interface ScheduleCreator {
    suspend fun createWorkSchedule(date: LocalDate): Set<LocalDate>
}