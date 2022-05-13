package com.github.googelfist.workshedule.presentation.config.models

import com.github.googelfist.workshedule.domain.models.DayType

sealed class ConfigEvent {
    data class UpdateSchedulePattern(val schedulePattern: List<DayType>) : ConfigEvent()
    object RefreshSchedulePattern : ConfigEvent()
    data class RefreshFirstWorkDate(val firstWorkDate: String) : ConfigEvent()

    object CreateDayType : ConfigEvent()
    data class EditDayType(val position: Int, val dayType: DayType) : ConfigEvent()
    data class DeleteDayType(val position: Int) : ConfigEvent()
}
