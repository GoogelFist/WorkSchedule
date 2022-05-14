package com.github.googelfist.workshedule.data.datasource.local

import com.github.googelfist.workshedule.data.Mapper
import com.github.googelfist.workshedule.data.datasource.LocalDataSource
import com.github.googelfist.workshedule.data.datasource.local.model.FirstWorkDateDao
import com.github.googelfist.workshedule.data.datasource.local.model.ScheduleTypeDao
import com.github.googelfist.workshedule.domain.monthgenerator.DateNowContainer
import java.time.LocalDate
import javax.inject.Inject

class RoomDataSourceImpl @Inject constructor(
    private val parametersDao: ParametersDao,
    private val dateNowContainer: DateNowContainer,
    private val mapper: Mapper
) : LocalDataSource {
    override suspend fun loadFirstWorkDate(): LocalDate {
        val firstWorkDate = parametersDao.loadFirstWorkDate(FIRST_WORK_DATE_ID)

        firstWorkDate?.let { return mapper.mapDateStringToLocalDate(it) }

        return dateNowContainer.getDate()
    }

    override suspend fun saveFirstWorkDate(firstWorkDate: String) {
        parametersDao.saveFirstWorkDate(FirstWorkDateDao(FIRST_WORK_DATE_ID, firstWorkDate))
    }

    // TODO:  change to schedule pattern methods
    override suspend fun loadScheduleType(): String {
        val scheduleType = parametersDao.loadScheduleType(SCHEDULE_TYPE_ID)
        scheduleType?.let {
            return it
        }
        return DEFAULT_SCHEDULE_TYPE
    }

    // TODO:  change to schedule pattern methods
    override suspend fun saveScheduleType(scheduleType: String) {
        parametersDao.saveScheduleType(ScheduleTypeDao(SCHEDULE_TYPE_ID, scheduleType))
    }

    companion object {
        private const val FIRST_WORK_DATE_ID = 1
        private const val SCHEDULE_TYPE_ID = 1

        private const val DEFAULT_SCHEDULE_TYPE = "Default"
    }
}