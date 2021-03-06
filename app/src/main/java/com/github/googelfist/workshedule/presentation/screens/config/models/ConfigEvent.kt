package com.github.googelfist.workshedule.presentation.screens.config.models

import com.github.googelfist.workshedule.domain.models.DayType

sealed class ConfigEvent {
    data class SavePatternName(val name: String) : ConfigEvent()
    data class SaveFirstWorkDate(val firstWorkDate: String) : ConfigEvent()
    data class SaveCurrentConfigId(val id: Int) : ConfigEvent()

    object CreateConfig : ConfigEvent()
    data class DeleteConfig(val id: Int) : ConfigEvent()

    object CreateDayType : ConfigEvent()
    data class EditDayType(val position: Int, val dayType: DayType) : ConfigEvent()
    data class DeleteDayType(val position: Int) : ConfigEvent()

    data class SaveDatTypeState(val dayType: DayType, val position: Int) : ConfigEvent()
}
