package com.github.googelfist.workshedule.domain

import com.github.googelfist.workshedule.domain.models.Day
import com.github.googelfist.workshedule.domain.models.DayType
import com.github.googelfist.workshedule.domain.models.GenerateConfig
import com.github.googelfist.workshedule.domain.models.ScheduleConfig

interface Repository {
    suspend fun saveNameSchedulePattern(name: String)
    suspend fun saveFirstWorkDate(firstWorkDate: String)

    suspend fun createDayType()
    suspend fun updateDayType(position: Int, dayType: DayType)
    suspend fun deleteDayType(position: Int)

    suspend fun loadScheduleConfig(): ScheduleConfig
    suspend fun loadGenerateConfig(): GenerateConfig

    fun putToCache(formattedDate: String, dayList: List<Day>)
    fun getFromCache(formattedDate: String): List<Day>?
}