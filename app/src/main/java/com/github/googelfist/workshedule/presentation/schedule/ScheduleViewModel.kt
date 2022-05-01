package com.github.googelfist.workshedule.presentation.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.googelfist.workshedule.domain.models.ScheduleState
import com.github.googelfist.workshedule.domain.models.ScheduleTypeState
import com.github.googelfist.workshedule.domain.usecases.GenerateCurrentMonthUseCase
import com.github.googelfist.workshedule.domain.usecases.GenerateNextMonthUseCase
import com.github.googelfist.workshedule.domain.usecases.GeneratePreviousMonthUseCase
import com.github.googelfist.workshedule.domain.usecases.LoadScheduleTypeUseCase
import com.github.googelfist.workshedule.domain.usecases.SaveFirstWorkDateUseCase
import com.github.googelfist.workshedule.domain.usecases.SaveScheduleTypeUseCase
import com.github.googelfist.workshedule.presentation.EventHandler
import com.github.googelfist.workshedule.presentation.schedule.models.ScheduleEvent
import kotlinx.coroutines.launch

class ScheduleViewModel(
    private val generatePreviousMonthUseCase: GeneratePreviousMonthUseCase,
    private val generateCurrentMonthUseCase: GenerateCurrentMonthUseCase,
    private val generateNextMonthUseCase: GenerateNextMonthUseCase,
    private val saveFirstWorkDateUseCase: SaveFirstWorkDateUseCase,
    private val saveScheduleTypeUseCase: SaveScheduleTypeUseCase,
    private val loadScheduleTypeUseCase: LoadScheduleTypeUseCase
) : ViewModel(), EventHandler<ScheduleEvent> {

    private var _scheduleState = MutableLiveData<ScheduleState>()
    val scheduleState: LiveData<ScheduleState>
        get() = _scheduleState

    private var _scheduleTypeState = MutableLiveData<ScheduleTypeState>()
    val scheduleTypeState: LiveData<ScheduleTypeState>
        get() = _scheduleTypeState

    init {
        loadScheduleType()
        generatedCurrentMonth()
        initMonth()
    }

    override fun obtainEvent(event: ScheduleEvent) {
        when (event) {
            ScheduleEvent.GeneratedCurrentMonth -> generatedCurrentMonth()
            ScheduleEvent.GeneratedNextMonth -> generatedNextMonth()
            ScheduleEvent.GeneratedPreviousMonth -> generatedPreviousMonth()
            is ScheduleEvent.RefreshFirstWorkDate -> refreshedFirstWorkDate(event.firstWorkDate)
            is ScheduleEvent.RefreshScheduleType -> refreshedScheduleType(event.scheduleType)
        }
    }

    private fun generatedCurrentMonth() {
        viewModelScope.launch {
            val scheduleState = generateCurrentMonthUseCase()
            _scheduleState.value = scheduleState
        }
    }

    private fun generatedPreviousMonth() {
        viewModelScope.launch {
            val scheduleState = generatePreviousMonthUseCase()
            _scheduleState.value = scheduleState
        }
    }

    private fun generatedNextMonth() {
        viewModelScope.launch {
            val scheduleState = generateNextMonthUseCase()
            _scheduleState.value = scheduleState
        }
    }

    private fun refreshedScheduleType(scheduleType: String) {
        viewModelScope.launch {
            saveScheduleTypeUseCase(scheduleType)

            val scheduleState = generateCurrentMonthUseCase()
            _scheduleState.value = scheduleState

            val scheduleTypeState = loadScheduleTypeUseCase()
            _scheduleTypeState.value = scheduleTypeState
        }
    }

    private fun refreshedFirstWorkDate(firstWorkDate: String) {
        viewModelScope.launch {
            saveFirstWorkDateUseCase(firstWorkDate)

            val scheduleState = generateCurrentMonthUseCase()
            _scheduleState.value = scheduleState

            val scheduleTypeState = loadScheduleTypeUseCase()
            _scheduleTypeState.value = scheduleTypeState
        }
    }

    private fun initMonth() {
        viewModelScope.launch {
            _scheduleState.value = ScheduleState.LaunchingState
            val scheduleState = generateCurrentMonthUseCase()
            _scheduleState.value = scheduleState
        }
    }

    private fun loadScheduleType() {
        viewModelScope.launch {
            val scheduleTypeState = loadScheduleTypeUseCase()
            _scheduleTypeState.value = scheduleTypeState
        }
    }
}