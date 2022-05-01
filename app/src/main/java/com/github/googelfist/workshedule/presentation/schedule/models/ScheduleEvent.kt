package com.github.googelfist.workshedule.presentation.schedule.models

sealed class ScheduleEvent {
    object GeneratedPreviousMonth : ScheduleEvent()
    object GeneratedCurrentMonth : ScheduleEvent()
    object GeneratedNextMonth : ScheduleEvent()
    data class RefreshScheduleType(val scheduleType: String) : ScheduleEvent()
    data class RefreshFirstWorkDate(val firstWorkDate: String) : ScheduleEvent()
}
