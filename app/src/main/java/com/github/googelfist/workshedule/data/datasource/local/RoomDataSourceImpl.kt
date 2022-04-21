package com.github.googelfist.workshedule.data.datasource.local

import com.github.googelfist.workshedule.data.datasource.LocalDataSource
import com.github.googelfist.workshedule.data.datasource.local.model.FirstWorkDateDao
import com.github.googelfist.workshedule.data.datasource.local.model.ScheduleTypeDao
import com.github.googelfist.workshedule.domain.monthgenerator.DateNowContainer
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class RoomDataSourceImpl @Inject constructor(
    private val parametersDao: ParametersDao,
    private val dateNowContainer: DateNowContainer
) :
    LocalDataSource {
    override suspend fun loadFirstWorkDate(): LocalDate {
        val firstWorkDate = parametersDao.loadFirstWorkDate(FIRST_WORK_DATE_ID)
        firstWorkDate?.let {
            // TODO: rewrite with mapper
            return LocalDate.from(DateTimeFormatter.ofPattern("yyyy-M-dd").parse(firstWorkDate))
        }
        return dateNowContainer.getDate()
    }

    override suspend fun saveFirstWorkDate(firstWorkDate: String) {
        parametersDao.saveFirstWorkDate(FirstWorkDateDao(FIRST_WORK_DATE_ID, firstWorkDate))
    }

    override suspend fun loadScheduleType(): String {
        // TODO: rewrite with sealed class
        val scheduleType = parametersDao.loadScheduleType(SCHEDULE_TYPE_ID)
        scheduleType?.let {
            return scheduleType
        }
        return DEFAULT_SCHEDULE_TYPE
    }

    override suspend fun saveScheduleType(scheduleType: String) {
        parametersDao.saveScheduleType(ScheduleTypeDao(SCHEDULE_TYPE_ID, scheduleType))
    }

    companion object {
        private const val FIRST_WORK_DATE_ID = 1
        private const val SCHEDULE_TYPE_ID = 1

        private const val DEFAULT_SCHEDULE_TYPE = "Default"
    }
}