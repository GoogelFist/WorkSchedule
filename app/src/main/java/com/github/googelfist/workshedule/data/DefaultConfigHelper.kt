package com.github.googelfist.workshedule.data

import com.github.googelfist.workshedule.domain.formatter.DateFormatter
import com.github.googelfist.workshedule.domain.models.DayType
import com.github.googelfist.workshedule.domain.models.GenerateConfig
import com.github.googelfist.workshedule.domain.models.ScheduleConfig
import com.github.googelfist.workshedule.domain.monthgenerator.DateNowContainer
import javax.inject.Inject

class DefaultConfigHelper @Inject constructor(
    private val dateFormatter: DateFormatter,
    private val dateNowContainer: DateNowContainer
) {

    fun createDefaultPattern(): List<DayType> {
        return listOf(
            DayType(ONE_VALUE, DAY_BACKGROUND_COLOR, DAY_TITLE),
            DayType(TWO_VALUE, NIGHT_BACKGROUND_COLOR, NIGHT_TITLE),
            DayType(TREE_VALUE, SLEEP_OFF_BACKGROUND_COLOR, SLEEP_OFF_TITLE),
            DayType(FOUR_VALUE, DAY_OFF_BACKGROUND_COLOR, DAY_OFF_TITLE)
        )
    }

    fun createDefaultScheduleConfig(): ScheduleConfig {
        return ScheduleConfig(
            id = DEFAULT_ID,
            configName = createDefaultConfigName(),
            firstWorkDate = dateFormatter.formatDateToConfig(dateNowContainer.getDate()),
            schedulePattern = createDefaultPattern()
        )
    }

    fun createDefaultGenerateConfig(): GenerateConfig {
        return GenerateConfig(
            id = DEFAULT_ID,
            firstWorkDate = dateNowContainer.getDate(),
            schedulePattern = listOf(createDefaultDayType())
        )
    }

    fun createDefaultDayType(): DayType {
        return DayType(id = NO_PATTERN_ID, title = DEFAULT_DAY_TITLE)
    }

    fun createDefaultConfigName(): String {
        return DEFAULT_CONFIG_NAME
    }

    companion object {

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