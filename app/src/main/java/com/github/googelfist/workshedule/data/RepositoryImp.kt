package com.github.googelfist.workshedule.data

import com.github.googelfist.workshedule.data.datasource.LocalDataSource
import com.github.googelfist.workshedule.domain.Repository
import com.github.googelfist.workshedule.domain.models.Day
import com.github.googelfist.workshedule.domain.models.DayType
import com.github.googelfist.workshedule.domain.models.GenerateConfig
import com.github.googelfist.workshedule.domain.models.ScheduleConfig
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val mapper: Mapper
) : Repository {

    private val cache = mutableMapOf<String, List<Day>>()

    private var schedulePattern = mutableListOf<DayType>()

    override suspend fun saveConfigName(name: String) {
        val currentConfigId = localDataSource.loadCurrentConfigId()
        localDataSource.saveConfigName(currentConfigId, name)
    }

    override suspend fun saveFirstWorkDate(firstWorkDate: String) {
        clearCache()
        val currentConfigId = localDataSource.loadCurrentConfigId()
        localDataSource.saveFirstWorkDate(currentConfigId, firstWorkDate)
    }

    override suspend fun loadScheduleConfig(): ScheduleConfig {
        val currentConfigId = localDataSource.loadCurrentConfigId()
        val configDao = localDataSource.loadConfigDao(currentConfigId)
        val scheduleConfig = mapper.mapConfigDaoToScheduleConfig(configDao)

        schedulePattern.clear()
        schedulePattern.addAll(scheduleConfig.schedulePattern)

        return scheduleConfig
    }

    override suspend fun loadGenerateConfig(): GenerateConfig {
        val currentConfigId = localDataSource.loadCurrentConfigId()
        val configDao = localDataSource.loadConfigDao(currentConfigId)
        return mapper.mapConfigDaoToGenerateConfig(configDao)
    }

    override suspend fun saveCurrentConfigId(id: Int) {
        localDataSource.saveCurrentConfigId(id)
    }

    // TODO: think it
    override suspend fun createDayType() {

        val nextId = if (schedulePattern.isEmpty()) {
            DEFAULT_ID
        } else {
            schedulePattern.maxOf { it.id } + ONE_VALUE
        }

        val dayType = DayType(id = nextId)
        schedulePattern.add(dayType)

        savePattern(schedulePattern)
    }

    override suspend fun updateDayType(position: Int, dayType: DayType) {

        schedulePattern.removeAt(position)
        schedulePattern.add(position, dayType)

        savePattern(schedulePattern)
    }

    override suspend fun deleteDayType(position: Int) {

        schedulePattern.removeAt(position)

        savePattern(schedulePattern)
    }

    override fun putToCache(formattedDate: String, dayList: List<Day>) {
        cache[formattedDate] = dayList
    }

    override fun getFromCache(formattedDate: String): List<Day>? {
        return cache[formattedDate]
    }

    override fun clearCache() {
        cache.clear()
    }

    private suspend fun savePattern(pattern: List<DayType>) {

        val currentConfigId = localDataSource.loadCurrentConfigId()
        val jsonPattern = mapper.mapListToJsonString(pattern)
        localDataSource.savePattern(currentConfigId, jsonPattern)
    }

    companion object {
        private const val ONE_VALUE = 1
        private const val DEFAULT_ID = 1
    }
}