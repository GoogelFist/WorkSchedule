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
    private val pattern = listOf<DayType>(
        DayType("#FFEF5350", "Day"),
        DayType("#FFEC407A", "Night"),
        DayType("#FFFFCA28", "Sleep${System.lineSeparator()}Off"),
        DayType("#FFD4E157", "Day${System.lineSeparator()}Off")
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

    // TODO: temp
    override fun createDayType() {
        val dayType = DayType("#FFFFFFFF", "Default${System.lineSeparator()}Day")
        schedulePattern.add(dayType)

        clearCache()
    }

    // TODO: temp
    override fun updateDayType(position: Int, dayType: DayType) {
        schedulePattern.removeAt(position)
        schedulePattern.add(position, dayType)

        clearCache()
    }

    // TODO: temp
    override fun deleteDayType(position: Int) {
        schedulePattern.removeAt(position)

        clearCache()
    }

    private fun clearCache() {
        cache.clear()
    }
}