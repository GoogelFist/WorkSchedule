package com.github.googelfist.workshedule.domain.monthgenerator

import java.time.LocalDate

interface ScheduleCreator {
    suspend fun createTwoInTwoSchedule(date: LocalDate): Set<LocalDate>
}