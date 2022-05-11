package com.github.googelfist.workshedule.presentation.schedule.models

import com.github.googelfist.workshedule.domain.models.DayType

sealed class ScheduleEvent {
    object GeneratedPreviousMonth : ScheduleEvent()
    object GeneratedCurrentMonth : ScheduleEvent()
    object GeneratedNextMonth : ScheduleEvent()
    data class UpdateSchedulePattern(val schedulePattern: List<DayType>) : ScheduleEvent()
    object RefreshSchedulePattern : ScheduleEvent()
    data class RefreshFirstWorkDate(val firstWorkDate: String) : ScheduleEvent()
    data class EditDayType(val position: Int, val dayType: DayType) : ScheduleEvent()

}
