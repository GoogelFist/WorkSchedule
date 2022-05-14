package com.github.googelfist.workshedule.data

import com.github.googelfist.workshedule.data.datasource.LocalDataSource
import com.github.googelfist.workshedule.domain.Repository
import com.github.googelfist.workshedule.domain.formatter.DateFormatter
import com.github.googelfist.workshedule.domain.models.Day
import com.github.googelfist.workshedule.domain.models.DayType
import com.github.googelfist.workshedule.domain.models.GenerateConfig
import com.github.googelfist.workshedule.domain.models.ScheduleConfig
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val dateFormatter: DateFormatter
) : Repository {

    private val cache = mutableMapOf<String, List<Day>>()

    private var schedulePattern = mutableListOf<DayType>()

    private var schedulePatternName = "Schedule Pattern 1"

    // TODO: temp
    private val pattern = listOf<DayType>(
        DayType("#FFEF5350", "Day"),
        DayType("#FFEC407A", "Night"),
        DayType("#FFFFCA28", "Sleep${System.lineSeparator()}Off"),
        DayType("#FFD4E157", "Day${System.lineSeparator()}Off")
    )

    // TODO: temp
    override suspend fun saveNameSchedulePattern(name: String) {
        schedulePatternName = name
    }

    override suspend fun saveFirstWorkDate(firstWorkDate: String) {
        clearCache()
        localDataSource.saveFirstWorkDate(firstWorkDate)
    }

    override suspend fun loadScheduleConfig(): ScheduleConfig {
        val firstWorkDate = localDataSource.loadFirstWorkDate()
        // TODO: temp
        if (schedulePattern.isEmpty()) {
            schedulePattern.addAll(pattern)
        }
        return ScheduleConfig(
            schedulePatternName = schedulePatternName,
            firstWorkDate = dateFormatter.formatDateToConfig(firstWorkDate),
            schedulePattern = schedulePattern
        )
    }

    override suspend fun loadGenerateConfig(): GenerateConfig {
        val firstWorkDate = localDataSource.loadFirstWorkDate()
        // TODO: temp
        if (schedulePattern.isEmpty()) {
            schedulePattern.addAll(pattern)
        }
        return GenerateConfig(
            firstWorkDate = firstWorkDate,
            schedulePattern = schedulePattern
        )
    }

    // TODO: temp
    override suspend fun createDayType() {
        val dayType = DayType("#FFFFFFFF", "Default${System.lineSeparator()}Day")
        schedulePattern.add(dayType)

        clearCache()
    }

    // TODO: temp
    override suspend fun updateDayType(position: Int, dayType: DayType) {
        schedulePattern.removeAt(position)
        schedulePattern.add(position, dayType)

        clearCache()
    }

    // TODO: temp
    override suspend fun deleteDayType(position: Int) {
        schedulePattern.removeAt(position)
        clearCache()
    }

    override fun putToCache(formattedDate: String, dayList: List<Day>) {
        cache[formattedDate] = dayList
    }

    override fun getFromCache(formattedDate: String): List<Day>? {
        return cache[formattedDate]
    }

    private fun clearCache() {
        cache.clear()
    }
}