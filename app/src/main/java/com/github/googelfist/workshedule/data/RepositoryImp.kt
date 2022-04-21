package com.github.googelfist.workshedule.data

import androidx.lifecycle.LiveData
import com.github.googelfist.workshedule.data.datasource.LocalDataSource
import com.github.googelfist.workshedule.domain.Repository
import java.time.LocalDate
import javax.inject.Inject

class RepositoryImp @Inject constructor(private val localDataSource: LocalDataSource) : Repository {
    override fun loadFirstWorkDate(): LiveData<LocalDate> {
        return localDataSource.loadFirstWorkDate()
    }

    override fun saveFirstWorkDate(firstWorkDate: String) {
        localDataSource.saveFirstWorkDate(firstWorkDate)
    }

    override fun loadScheduleType(): LiveData<String> {
        return localDataSource.loadScheduleType()
    }

    override fun saveScheduleType(scheduleType: String) {
        localDataSource.saveScheduleType(scheduleType)
    }
}