package com.github.googelfist.workshedule.domain

import java.time.LocalDate

interface Repository {
    suspend fun loadFirstWorkDate(): LocalDate
    suspend fun saveFirstWorkDate(firstWorkDate: String)

    suspend fun loadScheduleType(): String
    suspend fun saveScheduleType(scheduleType: String)
}