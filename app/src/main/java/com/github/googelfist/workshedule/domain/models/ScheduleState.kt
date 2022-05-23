package com.github.googelfist.workshedule.domain.models

sealed class ScheduleState {
    object Launching : ScheduleState()
    data class Generated(val formattedDate: String, val dayList: List<Day>) : ScheduleState()
}
