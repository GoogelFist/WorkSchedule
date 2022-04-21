package com.github.googelfist.workshedule.data.datasource.local

import com.github.googelfist.workshedule.data.datasource.LocalDataSource
import com.github.googelfist.workshedule.data.datasource.local.model.FirstWorkDateDao
import com.github.googelfist.workshedule.data.datasource.local.model.ScheduleTypeDao
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class RoomDataSourceImpl @Inject constructor(private val parametersDao: ParametersDao) :
    LocalDataSource {
    override suspend fun loadFirstWorkDate(): LocalDate {
        val firstWorkDate = parametersDao.loadFirstWorkDate(FIRST_WORK_DATE_ID)
        // TODO: rewrite with mapper
        return LocalDate.from(DateTimeFormatter.ofPattern("yyyy-M-dd").parse(firstWorkDate))
    }

    override suspend fun saveFirstWorkDate(firstWorkDate: String) {
        parametersDao.saveFirstWorkDate(FirstWorkDateDao(FIRST_WORK_DATE_ID, firstWorkDate))
    }

    override suspend fun loadScheduleType(): String {
        // TODO: rewrite with sealed class
        return parametersDao.loadScheduleType(SCHEDULE_TYPE_ID)
    }

    override suspend fun saveScheduleType(scheduleType: String) {
        parametersDao.saveScheduleType(ScheduleTypeDao(SCHEDULE_TYPE_ID, scheduleType))
    }

    companion object {
        private const val FIRST_WORK_DATE_ID = 1
        private const val SCHEDULE_TYPE_ID = 1
    }
}