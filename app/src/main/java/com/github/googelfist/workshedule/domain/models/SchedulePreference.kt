package com.github.googelfist.workshedule.domain.models

data class SchedulePreference(
    val schedule: String = DEFAULT_SCHEDULE
)

private const val DEFAULT_SCHEDULE = "Default schedule"