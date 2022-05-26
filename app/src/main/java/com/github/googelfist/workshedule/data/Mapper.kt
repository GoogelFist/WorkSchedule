package com.github.googelfist.workshedule.data

import com.github.googelfist.workshedule.data.datasource.local.model.ConfigDao
import com.github.googelfist.workshedule.domain.formatter.DateFormatter
import com.github.googelfist.workshedule.domain.models.Config
import com.github.googelfist.workshedule.domain.models.DayType
import com.github.googelfist.workshedule.domain.models.GenerateConfig
import com.github.googelfist.workshedule.domain.models.ScheduleConfig
import com.github.googelfist.workshedule.domain.monthgenerator.DateNowContainer
import com.google.gson.Gson
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class Mapper @Inject constructor(
    private val dateNowContainer: DateNowContainer,
    private val dateFormatter: DateFormatter,
    private val defaultConfigHelper: DefaultConfigHelper
) {

    fun mapConfigDaoToScheduleConfig(configDao: ConfigDao?): ScheduleConfig {
        if (configDao == null) {
            return defaultConfigHelper.createDefaultScheduleConfig()
        }
        return ScheduleConfig(
            id = configDao.id,
            configName = configDao.configName ?: defaultConfigHelper.createDefaultConfigName(),
            firstWorkDate = dateFormatter.formatDateToConfig(mapDateStringToLocalDate(configDao.firstWorkDate)),
            schedulePattern = mapJsonStringToList(configDao.schedulePattern)
        )
    }

    fun mapConfigDaoToGenerateConfig(configDao: ConfigDao?): GenerateConfig {
        if (configDao == null) {
            return defaultConfigHelper.createDefaultGenerateConfig()
        }
        return GenerateConfig(
            id = configDao.id,
            firstWorkDate = mapDateStringToLocalDate(configDao.firstWorkDate),
            schedulePattern = mapJsonStringToList(configDao.schedulePattern)
        )
    }

    fun mapListDaoToConfigList(currentId: Int, listDao: List<ConfigDao>): List<Config> {
        return listDao.map { mapConfigDaoToConfig(currentId, it) }
    }

    fun mapListToJsonString(value: List<DayType>?): String {
        return Gson().toJson(value)
    }

    private fun mapJsonStringToList(value: String?): List<DayType> {
        if (value == null) {
            return defaultConfigHelper.createDefaultPattern()
        }
        return Gson().fromJson(value, Array<DayType>::class.java).toList()
    }

    private fun mapDateStringToLocalDate(string: String?): LocalDate {
        if (string == null) {
            return dateNowContainer.getDate()
        }
        return LocalDate.from(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN).parse(string))
    }

    private fun mapConfigDaoToConfig(currentId: Int, configDao: ConfigDao): Config {
        val name = configDao.configName ?: defaultConfigHelper.createDefaultConfigName()
        val isCurrent = (configDao.id == currentId)
        return Config(configDao.id, name, isCurrent)
    }

    companion object {
        private const val DATE_FORMAT_PATTERN = "yyyy-M-d"
    }
}