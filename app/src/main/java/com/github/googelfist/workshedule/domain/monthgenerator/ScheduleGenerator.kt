package com.github.googelfist.workshedule.domain.monthgenerator

import com.github.googelfist.workshedule.domain.models.ScheduleState

interface ScheduleGenerator {
    suspend fun getCurrentMonthState(): ScheduleState
    suspend fun getPreviousMonthState(): ScheduleState
    suspend fun getNextMonthState(): ScheduleState
}