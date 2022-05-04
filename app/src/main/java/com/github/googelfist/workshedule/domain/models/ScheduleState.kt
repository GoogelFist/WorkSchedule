package com.github.googelfist.workshedule.domain.models

import com.github.googelfist.workshedule.domain.models.day.Day

sealed class ScheduleState {
    object LaunchingState : ScheduleState()
    data class GeneratedState(val formattedDate: String, val dayList: List<Day>) : ScheduleState()
}
