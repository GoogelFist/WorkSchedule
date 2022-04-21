package com.github.googelfist.workshedule.data

import com.github.googelfist.workshedule.data.datasource.LocalDataSource
import com.github.googelfist.workshedule.domain.Repository
import java.time.LocalDate
import javax.inject.Inject

class RepositoryImp @Inject constructor(private val localDataSource: LocalDataSource) : Repository {
    override suspend fun loadFirstWorkDate(): LocalDate {
        return localDataSource.loadFirstWorkDate()
    }

    override suspend fun saveFirstWorkDate(firstWorkDate: String) {
        localDataSource.saveFirstWorkDate(firstWorkDate)
    }

    override suspend fun loadScheduleType(): String {
        return localDataSource.loadScheduleType()
    }

    override suspend fun saveScheduleType(scheduleType: String) {
        localDataSource.saveScheduleType(scheduleType)
    }
}