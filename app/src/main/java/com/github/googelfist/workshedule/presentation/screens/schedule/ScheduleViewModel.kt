package com.github.googelfist.workshedule.presentation.screens.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.googelfist.workshedule.domain.models.ScheduleState
import com.github.googelfist.workshedule.domain.usecases.GetCurrentMonthStateUseCase
import com.github.googelfist.workshedule.domain.usecases.GetNextMonthStateUseCase
import com.github.googelfist.workshedule.domain.usecases.GetPreviousMonthStateUseCase
import com.github.googelfist.workshedule.presentation.EventHandler
import com.github.googelfist.workshedule.presentation.screens.schedule.models.ScheduleEvent
import kotlinx.coroutines.launch

class ScheduleViewModel(
    private val getPreviousMonthStateUseCase: GetPreviousMonthStateUseCase,
    private val getCurrentMonthStateUseCase: GetCurrentMonthStateUseCase,
    private val getNextMonthStateUseCase: GetNextMonthStateUseCase
) : ViewModel(), EventHandler<ScheduleEvent> {

    private var _scheduleState = MutableLiveData<ScheduleState>()
    val scheduleState: LiveData<ScheduleState>
        get() = _scheduleState

    init {
        initMonth()
    }

    override fun obtainEvent(event: ScheduleEvent) {
        when (event) {
            ScheduleEvent.GeneratedCurrentMonth -> generatedCurrentMonth()
            ScheduleEvent.GeneratedNextMonth -> generatedNextMonth()
            ScheduleEvent.GeneratedPreviousMonth -> generatedPreviousMonth()
            ScheduleEvent.RefreshMonth -> refreshMonth()
        }
    }

    private fun initMonth() {
        viewModelScope.launch {
            _scheduleState.value = ScheduleState.LaunchingState
            generatedCurrentMonth()
        }
    }

    private fun generatedCurrentMonth() {
        viewModelScope.launch {
            val scheduleState = getCurrentMonthStateUseCase()
            _scheduleState.value = scheduleState
        }
    }

    private fun generatedNextMonth() {
        viewModelScope.launch {
            val scheduleState = getNextMonthStateUseCase()
            _scheduleState.value = scheduleState
        }
    }

    private fun generatedPreviousMonth() {
        viewModelScope.launch {
            val scheduleState = getPreviousMonthStateUseCase()
            _scheduleState.value = scheduleState
        }
    }

    private fun refreshMonth() {
        generatedCurrentMonth()
    }
}