package com.github.googelfist.workschedule.domain.models

data class PreferencesDTO(
    val scheduleType: String,
    val firstWorkDate: String,
    val actualFirstWorkDate: String
)
