package com.github.googelfist.workshedule.domain

interface Repository {
    suspend fun saveFirstWorkDate(firstWorkDate: String)

    suspend fun loadScheduleType(): ScheduleType

    suspend fun saveScheduleType(scheduleType: String)
}