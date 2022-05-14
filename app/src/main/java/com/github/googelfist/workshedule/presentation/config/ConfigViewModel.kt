package com.github.googelfist.workshedule.presentation.config

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.googelfist.workshedule.domain.models.DayType
import com.github.googelfist.workshedule.domain.usecases.CreateDayTypeUseCase
import com.github.googelfist.workshedule.domain.usecases.DeleteDayTypeUseCase
import com.github.googelfist.workshedule.domain.usecases.EditDayTypeUseCase
import com.github.googelfist.workshedule.domain.usecases.LoadFirstWorkDateUseCase
import com.github.googelfist.workshedule.domain.usecases.LoadSchedulePatternUseCase
import com.github.googelfist.workshedule.domain.usecases.SaveFirstWorkDateUseCase
import com.github.googelfist.workshedule.domain.usecases.SaveSchedulePatternUseCase
import com.github.googelfist.workshedule.presentation.EventHandler
import com.github.googelfist.workshedule.presentation.config.models.ConfigEvent
import kotlinx.coroutines.launch

class ConfigViewModel(
    private val saveFirstWorkDateUseCase: SaveFirstWorkDateUseCase,
    private val loadFirstWorkDateUseCase: LoadFirstWorkDateUseCase,
    private val saveSchedulePatternUseCase: SaveSchedulePatternUseCase,
    private val loadSchedulePatternUseCase: LoadSchedulePatternUseCase,
    private val editDayTypeUseCase: EditDayTypeUseCase,
    private val createDayTypeUseCase: CreateDayTypeUseCase,
    private val deleteDayTypeUseCase: DeleteDayTypeUseCase
) : ViewModel(), EventHandler<ConfigEvent> {

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
            is ConfigEvent.RefreshFirstWorkDate -> refreshedFirstWorkDate(event.firstWorkDate)
            ConfigEvent.UpdateSchedulePattern -> updatedSchedulePattern()

            ConfigEvent.CreateDayType -> createdDayType()
            is ConfigEvent.EditDayType -> updatedDayType(event.position, event.dayType)
            is ConfigEvent.DeleteDayType -> deletedDayType(event.position)
        }
    }

    private fun initConfig() {
        viewModelScope.launch {

            val firstDate = loadFirstWorkDateUseCase()
            _firstWorkDate.value = firstDate

            val patternSchedule = loadSchedulePatternUseCase()
            _scheduleTypePattern.value = patternSchedule
        }
    }

    private fun refreshedFirstWorkDate(firstWorkDate: String) {
        viewModelScope.launch {
            saveFirstWorkDateUseCase(firstWorkDate)

            val firstDate = loadFirstWorkDateUseCase()
            _firstWorkDate.value = firstDate
        }
    }

    private fun updatedSchedulePattern() {
        viewModelScope.launch {

            val scheduleTypePattern = loadSchedulePatternUseCase()
            _scheduleTypePattern.value = scheduleTypePattern
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