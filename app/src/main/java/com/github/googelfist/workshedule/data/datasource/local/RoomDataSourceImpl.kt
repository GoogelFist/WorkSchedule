package com.github.googelfist.workshedule.data.datasource.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.github.googelfist.workshedule.data.datasource.LocalDataSource
import com.github.googelfist.workshedule.data.datasource.local.model.FirstWorkDateDao
import com.github.googelfist.workshedule.data.datasource.local.model.ScheduleTypeDao
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class RoomDataSourceImpl @Inject constructor(private val parametersDao: ParametersDao) :
    LocalDataSource {
    override fun loadFirstWorkDate(): LiveData<LocalDate> {
        val loadFirstWorkDate = parametersDao.loadFirstWorkDate(FIRST_WORK_DATE_ID)
        return Transformations.map(loadFirstWorkDate) {
            LocalDate.from(DateTimeFormatter.ofPattern("yyyy-M-dd").parse(it))
        }
    }

    override fun saveFirstWorkDate(firstWorkDate: String) {
        parametersDao.saveFirstWorkDate(FirstWorkDateDao(FIRST_WORK_DATE_ID, firstWorkDate))
    }

    override fun loadScheduleType(): LiveData<String> {
        return parametersDao.loadScheduleType(SCHEDULE_TYPE_ID)
    }

    override fun saveScheduleType(scheduleType: String) {
        parametersDao.saveScheduleType(ScheduleTypeDao(SCHEDULE_TYPE_ID, scheduleType))
    }

    companion object {
        private const val FIRST_WORK_DATE_ID = 1
        private const val SCHEDULE_TYPE_ID = 1
    }
}