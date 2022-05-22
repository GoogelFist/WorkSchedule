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
) {

    fun mapConfigDaoToScheduleConfig(configDao: ConfigDao?): ScheduleConfig {
        if (configDao == null) {
            return ScheduleConfig(
                id = DEFAULT_ID,
                configName = DEFAULT_CONFIG_NAME.plus(DEFAULT_ID),
                firstWorkDate = dateFormatter.formatDateToConfig(dateNowContainer.getDate()),
                schedulePattern = createDefaultPattern()
            )
        }
        return ScheduleConfig(
            id = configDao.id,
            configName = configDao.configName ?: DEFAULT_CONFIG_NAME.plus(configDao.id),
            firstWorkDate = dateFormatter.formatDateToConfig(mapDateStringToLocalDate(configDao.firstWorkDate)),
            schedulePattern = mapJsonStringToList(configDao.schedulePattern)
        )
    }

    //todo think about another logic
    fun mapConfigDaoToGenerateConfig(configDao: ConfigDao?): GenerateConfig {
        if (configDao == null) {
            return GenerateConfig(
                id = DEFAULT_ID,
                firstWorkDate = dateNowContainer.getDate(),
                schedulePattern = createDefaultPattern()
            )
        }
        var pattern = mapJsonStringToList(configDao.schedulePattern)
        if (pattern.isEmpty()) {
            pattern = listOf(DayType(id = NO_PATTERN_ID, title = DEFAULT_DAY_TITLE))
        }
        return GenerateConfig(
            id = configDao.id,
            firstWorkDate = mapDateStringToLocalDate(configDao.firstWorkDate),
            schedulePattern = pattern
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
            return createDefaultPattern()
        }
        return Gson().fromJson(value, Array<DayType>::class.java).toList()
    }

    private fun mapDateStringToLocalDate(string: String?): LocalDate {
        if (string == null) return dateNowContainer.getDate()
        return LocalDate.from(DateTimeFormatter.ofPattern(PATTERN).parse(string))
    }

    private fun mapConfigDaoToConfig(currentId: Int, configDao: ConfigDao): Config {
        val name = configDao.configName ?: DEFAULT_CONFIG_NAME.plus(configDao.id)
        val isCurrent = (configDao.id == currentId)
        return Config(configDao.id, name, isCurrent)
    }

    private fun createDefaultPattern(): List<DayType> {
        return listOf(
            DayType(ONE_VALUE, DAY_BACKGROUND_COLOR, DAY_TITLE),
            DayType(TWO_VALUE, NIGHT_BACKGROUND_COLOR, NIGHT_TITLE),
            DayType(TREE_VALUE, SLEEP_OFF_BACKGROUND_COLOR, SLEEP_OFF_TITLE),
            DayType(FOUR_VALUE, DAY_OFF_BACKGROUND_COLOR, DAY_OFF_TITLE)
        )
    }

    companion object {
        private const val PATTERN = "yyyy-M-d"

        private const val DEFAULT_DAY_TITLE = ""

        private const val DEFAULT_ID = 1
        private const val NO_PATTERN_ID = 0

        private const val ONE_VALUE = 1
        private const val TWO_VALUE = 2
        private const val TREE_VALUE = 3
        private const val FOUR_VALUE = 4

        private const val DEFAULT_CONFIG_NAME = "Schedule Pattern"

        private const val DAY_BACKGROUND_COLOR = "#FFEF5350"
        private const val DAY_TITLE = "Day"

        private const val NIGHT_BACKGROUND_COLOR = "#FFEC407A"
        private const val NIGHT_TITLE = "Night"

        private const val SLEEP_OFF_BACKGROUND_COLOR = "#FFFFCA28"
        private const val SLEEP_OFF_TITLE = "Sleep\nOff"

        private const val DAY_OFF_BACKGROUND_COLOR = "#FFD4E157"
        private const val DAY_OFF_TITLE = "Day\nOff"
    }
}