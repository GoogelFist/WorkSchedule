package com.github.googelfist.workshedule.data

import com.github.googelfist.workshedule.data.datasource.LocalDataSource
import com.github.googelfist.workshedule.domain.Repository
import com.github.googelfist.workshedule.domain.ScheduleType
import javax.inject.Inject

class RepositoryImp @Inject constructor(private val localDataSource: LocalDataSource) : Repository {

    override suspend fun saveFirstWorkDate(firstWorkDate: String) {
        localDataSource.saveFirstWorkDate(firstWorkDate)
    }

    override suspend fun loadScheduleType(): ScheduleType {
        val scheduleType = localDataSource.loadScheduleType()
        return when (scheduleType) {
            TWO_IN_TWO_SCHEDULE_TYPE -> {
                val firstWorkDate = localDataSource.loadFirstWorkDate()
                ScheduleType.TwoInTwo(firstWorkDate = firstWorkDate, TWO_IN_TWO_CYCLE_DAY_STEP)
            }
            DEFAULT_SCHEDULE_TYPE -> ScheduleType.Default
            else -> {
                throw RuntimeException("Unknown schedule type")
            }
        }
    }

    override suspend fun saveScheduleType(scheduleType: String) {
        localDataSource.saveScheduleType(scheduleType)
    }

    companion object {

        private const val DEFAULT_SCHEDULE_TYPE = "Default"
        private const val TWO_IN_TWO_SCHEDULE_TYPE = "2/2"

        private const val TWO_IN_TWO_CYCLE_DAY_STEP = 4L
    }
}