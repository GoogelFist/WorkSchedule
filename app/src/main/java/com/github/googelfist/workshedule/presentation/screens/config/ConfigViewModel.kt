package com.github.googelfist.workshedule.presentation.screens.config

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.googelfist.workshedule.domain.models.Config
import com.github.googelfist.workshedule.domain.models.DayType
import com.github.googelfist.workshedule.domain.models.ScheduleConfig
import com.github.googelfist.workshedule.domain.usecases.*
import com.github.googelfist.workshedule.presentation.EventHandler
import com.github.googelfist.workshedule.presentation.screens.config.models.ConfigEvent
import com.github.googelfist.workshedule.presentation.screens.config.models.ConfigState
import com.github.googelfist.workshedule.presentation.screens.configlist.models.ConfigListState
import kotlinx.coroutines.launch

class ConfigViewModel(
    private val saveCurrentConfigIdUseCase: SaveCurrentConfigIdUseCase,
    private val saveFirstWorkDateUseCase: SaveFirstWorkDateUseCase,
    private val saveConfigNameUseCase: SaveConfigNameUseCase,
    private val loadScheduleConfigUseCase: LoadScheduleConfigUseCase,
    private val loadConfigListUseCase: LoadConfigListUseCase,
    private val createConfigUseCase: CreateConfigUseCase,
    private val deleteConfigUseCase: DeleteConfigUseCase,
    private val createDayTypeUseCase: CreateDayTypeUseCase,
    private val editDayTypeUseCase: EditDayTypeUseCase,
    private val deleteDayTypeUseCase: DeleteDayTypeUseCase
) : ViewModel(), EventHandler<ConfigEvent> {

    private var _scheduleConfig = MutableLiveData<ScheduleConfig>()
    val scheduleConfig: LiveData<ScheduleConfig>
        get() = _scheduleConfig

    private var _configState = MutableLiveData<ConfigState>(ConfigState.NotEmptyDayTypesList)
    val configState: LiveData<ConfigState>
        get() = _configState

    private var _configListState = MutableLiveData<ConfigListState>(ConfigListState.NotEmptyList)
    val configListState: LiveData<ConfigListState>
        get() = _configListState

    private var _configList = MutableLiveData<List<Config>>()
    val configList: LiveData<List<Config>>
        get() = _configList

    init {
        initConfig()
    }

    override fun obtainEvent(event: ConfigEvent) {
        when (event) {
            is ConfigEvent.SaveFirstWorkDate -> savedFirstWorkDate(event.firstWorkDate)
            is ConfigEvent.SavePatternName -> savedConfigName(event.name)
            is ConfigEvent.SaveCurrentConfigId -> savedCurrentConfigId(event.id)

            is ConfigEvent.CreateConfig -> createdConfig()
            is ConfigEvent.DeleteConfig -> deletedConfig(event.id)

            ConfigEvent.CreateDayType -> createdDayType()
            is ConfigEvent.EditDayType -> editDayType(event.position, event.dayType)
            is ConfigEvent.DeleteDayType -> deletedDayType(event.position)
        }
    }


    private fun initConfig() {
        updatedConfigState()
        updatedConfigList()
    }

    private fun updatedConfigState() {

        viewModelScope.launch {
            val scheduleConfig = loadScheduleConfigUseCase()

            if (scheduleConfig.schedulePattern.isEmpty()) {
                _configState.value = ConfigState.EmptyDayTypesList
            } else {
                _configState.value = ConfigState.NotEmptyDayTypesList
            }

            _scheduleConfig.value = scheduleConfig
        }
    }

    private fun updatedConfigList() {
        viewModelScope.launch {
            val list = loadConfigListUseCase()

            if (list.isEmpty()) {
                _configListState.value = ConfigListState.EmptyList
            } else {
                _configListState.value = ConfigListState.NotEmptyList
            }

            _configList.value = list
        }
    }

    private fun savedFirstWorkDate(firstWorkDate: String) {
        viewModelScope.launch {
            saveFirstWorkDateUseCase(firstWorkDate)

            updatedConfigState()
        }
    }

    private fun savedConfigName(name: String) {
        viewModelScope.launch {
            saveConfigNameUseCase.invoke(name)

            updatedConfigState()
            updatedConfigList()
        }
    }

    private fun savedCurrentConfigId(id: Int) {
        viewModelScope.launch {
            saveCurrentConfigIdUseCase.invoke(id)

            updatedConfigList()
            updatedConfigState()
        }
    }

    private fun createdConfig() {
        viewModelScope.launch {
            createConfigUseCase()

            updatedConfigList()
        }
    }

    private fun deletedConfig(id: Int) {
        viewModelScope.launch {
            deleteConfigUseCase(id)
            updatedConfigList()
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