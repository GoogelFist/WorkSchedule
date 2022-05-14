package com.github.googelfist.workshedule.presentation.config.models

import com.github.googelfist.workshedule.domain.models.DayType

sealed class ConfigEvent {
    object UpdateConfigState : ConfigEvent()
    data class SavePatternName(val name: String) : ConfigEvent()
    data class SaveFirstWorkDate(val firstWorkDate: String) : ConfigEvent()

    object CreateDayType : ConfigEvent()
    data class EditDayType(val position: Int, val dayType: DayType) : ConfigEvent()
    data class DeleteDayType(val position: Int) : ConfigEvent()
}
