package com.github.googelfist.workshedule.domain.models

sealed class ScheduleState {
    object Launching : ScheduleState()
    data class GeneratedPrevious(val formattedDate: String, val dayList: List<Day>) :
        ScheduleState()

    data class GeneratedCurrent(val formattedDate: String, val dayList: List<Day>) : ScheduleState()
    data class GeneratedNext(val formattedDate: String, val dayList: List<Day>) : ScheduleState()
}
