package com.github.googelfist.workshedule.domain.models

sealed class ScheduleState {
    object LaunchingState : ScheduleState()
    data class GeneratedState(val formattedDate: String, val dayList: List<Day>) : ScheduleState()
}
