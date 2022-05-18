package com.github.googelfist.workshedule.presentation.config

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.googelfist.workshedule.domain.models.DayType
import com.github.googelfist.workshedule.domain.models.ScheduleConfig
import com.github.googelfist.workshedule.domain.usecases.*
import com.github.googelfist.workshedule.presentation.EventHandler
import com.github.googelfist.workshedule.presentation.config.models.ConfigEvent
import com.github.googelfist.workshedule.presentation.config.models.PatternState
import kotlinx.coroutines.launch

class ConfigViewModel(
    private val saveCurrentConfigIdUseCase: SaveCurrentConfigIdUseCase,
    private val saveFirstWorkDateUseCase: SaveFirstWorkDateUseCase,
    private val savePatternNameUseCase: SavePatternNameUseCase,
    private val loadScheduleConfigUseCase: LoadScheduleConfigUseCase,
    private val createDayTypeUseCase: CreateDayTypeUseCase,
    private val editDayTypeUseCase: EditDayTypeUseCase,
    private val deleteDayTypeUseCase: DeleteDayTypeUseCase
) : ViewModel(), EventHandler<ConfigEvent> {

    private var _scheduleConfig = MutableLiveData<ScheduleConfig>()
    val scheduleConfig: LiveData<ScheduleConfig>
        get() = _scheduleConfig

    private var _patternState = MutableLiveData<PatternState>(PatternState.NormalState)
    val patternState: LiveData<PatternState>
        get() = _patternState

    init {
        initConfig()
    }

    override fun obtainEvent(event: ConfigEvent) {
        when (event) {
            is ConfigEvent.SaveFirstWorkDate -> savedFirstWorkDate(event.firstWorkDate)
            is ConfigEvent.SavePatternName -> savedPatternName(event.name)
            is ConfigEvent.SaveCurrentConfigId -> savedCurrentConfigId(event.id)

            ConfigEvent.CreateDayType -> createdDayType()
            is ConfigEvent.EditDayType -> editDayType(event.position, event.dayType)
            is ConfigEvent.DeleteDayType -> deletedDayType(event.position)
        }
    }

    private fun initConfig() {
        updatedConfigState()
    }

    private fun updatedConfigState() {

        viewModelScope.launch {
            val scheduleConfig = loadScheduleConfigUseCase()
            if (scheduleConfig.schedulePattern.isEmpty()) {
                _patternState.value = PatternState.EmptyPattern
            } else {
                _patternState.value = PatternState.NormalState
            }
            _scheduleConfig.value = scheduleConfig
        }
    }

    private fun savedFirstWorkDate(firstWorkDate: String) {
        viewModelScope.launch {
            saveFirstWorkDateUseCase(firstWorkDate)

            updatedConfigState()
        }
    }

    private fun savedPatternName(name: String) {
        viewModelScope.launch {
            savePatternNameUseCase.invoke(name)

            updatedConfigState()
        }
    }

    private fun savedCurrentConfigId(id: Int) {
        viewModelScope.launch {
            saveCurrentConfigIdUseCase.invoke(id)
        }
    }

    private fun createdDayType() {
        viewModelScope.launch {
            createDayTypeUseCase()

            updatedConfigState()
        }
    }

    private fun editDayType(position: Int, dayType: DayType) {
        viewModelScope.launch {
            editDayTypeUseCase(position, dayType)

            updatedConfigState()
        }
    }

    private fun deletedDayType(position: Int) {
        viewModelScope.launch {
            deleteDayTypeUseCase(position)

            updatedConfigState()
        }
    }
}