package com.github.googelfist.workshedule.data.datasource

import java.time.LocalDate

interface LocalDataSource {
    suspend fun loadFirstWorkDate(): LocalDate
    suspend fun saveFirstWorkDate(firstWorkDate: String)

    suspend fun loadScheduleType(): String
    suspend fun saveScheduleType(scheduleType: String)
}