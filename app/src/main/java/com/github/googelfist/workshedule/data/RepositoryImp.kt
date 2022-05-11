package com.github.googelfist.workshedule.data

import com.github.googelfist.workshedule.data.datasource.LocalDataSource
import com.github.googelfist.workshedule.domain.Repository
import com.github.googelfist.workshedule.domain.models.Day
import com.github.googelfist.workshedule.domain.models.DayType
import java.time.LocalDate
import javax.inject.Inject

class RepositoryImp @Inject constructor(private val localDataSource: LocalDataSource) : Repository {

    private val cache =
        mutableMapOf<String, List<Day>>()

    private var schedulePattern = mutableListOf<DayType>()

    // TODO: temp
    private val pattern = listOf(
        DayType("#BF6F0101", "Day"),
        DayType("#FF018786", "Night"),
        DayType("#BF706B09", "SleepOff"),
        DayType("#BFFFF300", "Weekend")
    )

    override suspend fun saveFirstWorkDate(firstWorkDate: String) {
        localDataSource.saveFirstWorkDate(firstWorkDate)
        clearCache()
    }

    override suspend fun loadFirstWorkDate(): LocalDate {
        return localDataSource.loadFirstWorkDate()
    }

    override fun putToCache(
        formattedDate: String,
        dayList: List<Day>
    ) {
        cache[formattedDate] = dayList
    }

    override fun getFromCache(formattedDate: String): List<Day>? {
        return cache[formattedDate]
    }

    // TODO: temp
    override fun loadSchedulePattern(): List<DayType> {

        if (schedulePattern.isEmpty()) {
            schedulePattern.addAll(pattern)
        }

        return schedulePattern
    }

    // TODO: temp
    override fun saveSchedulePattern(pattern: List<DayType>) {
        schedulePattern.clear()
        schedulePattern.addAll(pattern)

        clearCache()
    }

    override fun editDayType(position: Int, dayType: DayType) {
        schedulePattern.removeAt(position)
        schedulePattern.add(position, dayType)

        clearCache()
    }

    private fun clearCache() {
        cache.clear()
    }
}