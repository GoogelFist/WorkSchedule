package com.github.googelfist.workshedule.domain

import com.github.googelfist.workshedule.domain.models.Config
import com.github.googelfist.workshedule.domain.models.DayType
import com.github.googelfist.workshedule.domain.models.GenerateConfig
import com.github.googelfist.workshedule.domain.models.ScheduleConfig

interface Repository {
    suspend fun saveConfigName(name: String)
    suspend fun saveFirstWorkDate(firstWorkDate: String)

    suspend fun createDayType()
    suspend fun updateDayType(position: Int, dayType: DayType)
    suspend fun deleteDayType(position: Int)

    suspend fun loadScheduleConfig(): ScheduleConfig
    suspend fun loadGenerateConfig(): GenerateConfig

    suspend fun saveCurrentConfigId(id: Int)
    suspend fun loadCurrentConfigId(): Int

    suspend fun loadConfigList(): List<Config>
    suspend fun createConfig()
    suspend fun deleteConfig(id: Int)
}