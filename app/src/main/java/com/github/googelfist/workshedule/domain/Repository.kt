package com.github.googelfist.workshedule.domain

import com.github.googelfist.workshedule.domain.models.ScheduleTypeState

interface Repository {
    suspend fun saveFirstWorkDate(firstWorkDate: String)

    suspend fun loadScheduleType(): ScheduleTypeState

    suspend fun saveScheduleType(scheduleType: String)
}