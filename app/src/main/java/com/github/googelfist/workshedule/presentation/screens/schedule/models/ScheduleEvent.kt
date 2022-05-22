package com.github.googelfist.workshedule.presentation.screens.schedule.models

sealed class ScheduleEvent {
    object GeneratedPreviousMonth : ScheduleEvent()
    object GeneratedCurrentMonth : ScheduleEvent()
    object GeneratedNextMonth : ScheduleEvent()
    object RefreshMonth : ScheduleEvent()
}
