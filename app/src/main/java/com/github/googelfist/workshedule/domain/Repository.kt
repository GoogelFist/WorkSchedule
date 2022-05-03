package com.github.googelfist.workshedule.domain

import com.github.googelfist.workshedule.domain.models.ScheduleTypeState
import com.github.googelfist.workshedule.domain.models.day.Day

interface Repository {
    suspend fun saveFirstWorkDate(firstWorkDate: String)

    suspend fun loadScheduleType(): ScheduleTypeState
    suspend fun saveScheduleType(scheduleType: String)

    fun putToCache(formattedDate: String, dayList: List<Day>)
    fun getFromCache(formattedDate: String): List<Day>?
}