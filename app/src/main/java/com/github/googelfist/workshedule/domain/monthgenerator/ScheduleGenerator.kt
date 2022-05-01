package com.github.googelfist.workshedule.domain.monthgenerator

import com.github.googelfist.workshedule.domain.models.ScheduleState

interface ScheduleGenerator {
    suspend fun generateCurrentMonth(): ScheduleState
    suspend fun generatePreviousMonth(): ScheduleState
    suspend fun generateNextMonth(): ScheduleState
}