package com.github.googelfist.workshedule.presentation.config

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.googelfist.workshedule.domain.models.DayType
import com.github.googelfist.workshedule.domain.usecases.CreateDayTypeUseCase
import com.github.googelfist.workshedule.domain.usecases.DeleteDayTypeUseCase
import com.github.googelfist.workshedule.domain.usecases.EditDayTypeUseCase
import com.github.googelfist.workshedule.domain.usecases.LoadScheduleConfigUseCase
import com.github.googelfist.workshedule.domain.usecases.SaveFirstWorkDateUseCase
import com.github.googelfist.workshedule.domain.usecases.SavePatternNameUseCase
import com.github.googelfist.workshedule.presentation.EventHandler
import com.github.googelfist.workshedule.presentation.config.models.ConfigEvent
import kotlinx.coroutines.launch

class ConfigViewModel(
    private val saveFirstWorkDateUseCase: SaveFirstWorkDateUseCase,
    private val savePatternNameUseCase: SavePatternNameUseCase,
    private val loadScheduleConfigUseCase: LoadScheduleConfigUseCase,
    private val createDayTypeUseCase: CreateDayTypeUseCase,
    private val editDayTypeUseCase: EditDayTypeUseCase,
    private val deleteDayTypeUseCase: DeleteDayTypeUseCase
) : ViewModel(), EventHandler<ConfigEvent> {

    private var _patternName = MutableLiveData<String>()
    val patternName: LiveData<String>
        get() = _patternName

    private var _scheduleTypePattern = MutableLiveData<List<DayType>>()
    val scheduleTypePattern: LiveData<List<DayType>>
        get() = _scheduleTypePattern

    private var _firstWorkDate = MutableLiveData<String>()
    val firstWorkDate: LiveData<String>
        get() = _firstWorkDate

    init {
        initConfig()
    }

    override fun obtainEvent(event: ConfigEvent) {
        when (event) {
            ConfigEvent.UpdateConfigState -> updatedConfigState()

            is ConfigEvent.SaveFirstWorkDate -> savedFirstWorkDate(event.firstWorkDate)
            is ConfigEvent.SavePatternName -> savedPatternName(event.name)

            ConfigEvent.CreateDayType -> createdDayType()
            is ConfigEvent.EditDayType -> updatedDayType(event.position, event.dayType)
            is ConfigEvent.DeleteDayType -> deletedDayType(event.position)
        }
    }

    private fun initConfig() {
        updatedConfigState()
    }

    private fun updatedConfigState() {
        viewModelScope.launch {
            val scheduleConfig = loadScheduleConfigUseCase()

            val name = scheduleConfig.schedulePatternName
            _patternName.value = name

            val firstDate = scheduleConfig.firstWorkDate
            _firstWorkDate.value = firstDate

            val patternSchedule = scheduleConfig.schedulePattern
            _scheduleTypePattern.value = patternSchedule
        }
    }

    private fun savedFirstWorkDate(firstWorkDate: String) {
        viewModelScope.launch {
            saveFirstWorkDateUseCase(firstWorkDate)
        }
    }

    private fun savedPatternName(name: String) {
        viewModelScope.launch {
            savePatternNameUseCase.invoke(name)
        }
    }

    private fun createdDayType() {
        viewModelScope.launch {
            createDayTypeUseCase()
        }
    }

    private fun updatedDayType(position: Int, dayType: DayType) {
        viewModelScope.launch {
            editDayTypeUseCase(position, dayType)
        }
    }

    private fun deletedDayType(position: Int) {
        viewModelScope.launch {
            deleteDayTypeUseCase(position)
        }
    }
}