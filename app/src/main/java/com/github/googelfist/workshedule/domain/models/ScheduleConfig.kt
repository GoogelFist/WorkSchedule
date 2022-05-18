package com.github.googelfist.workshedule.domain.models

data class ScheduleConfig(
    val id: Int,
    val configName: String,
    val firstWorkDate: String,
    val schedulePattern: List<DayType>
)