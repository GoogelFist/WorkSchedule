package com.github.googelfist.workshedule.data

import com.github.googelfist.workshedule.data.datasource.LocalDataSource
import com.github.googelfist.workshedule.domain.Repository
import com.github.googelfist.workshedule.domain.models.Day
import com.github.googelfist.workshedule.domain.models.ScheduleTypeState
import com.github.googelfist.workshedule.domain.monthgenerator.DayType
import javax.inject.Inject

class RepositoryImp @Inject constructor(private val localDataSource: LocalDataSource) : Repository {

    private val cache = mutableMapOf<String, List<Day>>()

    override suspend fun saveFirstWorkDate(firstWorkDate: String) {
        localDataSource.saveFirstWorkDate(firstWorkDate)
        clearCache()
    }

    // TODO: edit this logic
    override suspend fun loadScheduleType(): ScheduleTypeState {
        val scheduleType = localDataSource.loadScheduleType()
        return when (scheduleType) {
            TWO_IN_TWO_SCHEDULE_TYPE -> {
                val firstWorkDate = localDataSource.loadFirstWorkDate()
                ScheduleTypeState.TwoInTwo(
                    firstWorkDate = firstWorkDate,
                    dayCycleStep = TWO_IN_TWO_CYCLE_DAY_STEP
                )
            }
            DEFAULT_SCHEDULE_TYPE -> ScheduleTypeState.Default()
            else -> {
                throw RuntimeException("Unknown schedule type")
            }
        }
    }

    override suspend fun saveScheduleType(scheduleType: String) {
        localDataSource.saveScheduleType(scheduleType)
        clearCache()
    }

    override fun putToCache(formattedDate: String, dayList: List<Day>) {
        cache[formattedDate] = dayList
    }

    override fun getFromCache(formattedDate: String): List<Day>? {
        return cache[formattedDate]
    }

    // TODO: temp
    override fun getSchedulePattern(): List<DayType> {
//        val pattern = listOf(
//            DayType.WorkDay("#BF6F0101"),
//            DayType.DefaultDay("#FFFFFFFF")
//        )

        val pattern = listOf(
            DayType.WorkDay("#BF6F0101", "Day"),
            DayType.WorkNight("#FF018786", "Night"),
            DayType.WeekendDaySleepOff("#BF706B09", "SleepOff"),
            DayType.WeekendDay("#BFFFF300", "Weekend")
        )

        return pattern
    }

    private fun clearCache() {
        cache.clear()
    }

    companion object {

        private const val DEFAULT_SCHEDULE_TYPE = "Default"
        private const val TWO_IN_TWO_SCHEDULE_TYPE = "2/2"

        private const val TWO_IN_TWO_CYCLE_DAY_STEP = 4L
    }
}