package com.github.googelfist.workshedule.domain.models

data class ScheduleConfig(
    val schedulePatternName: String,
    val firstWorkDate: String,
    val schedulePattern: List<DayType>
)