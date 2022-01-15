package com.github.googelfist.workschedule.domain.models

data class PreferencesModel(
    val scheduleType: String,
    val firstWorkDate: String,
    val actualFirstWorkDate: String
)
