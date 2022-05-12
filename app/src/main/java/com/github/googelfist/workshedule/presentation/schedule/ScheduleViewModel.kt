package com.github.googelfist.workshedule.presentation.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.googelfist.workshedule.domain.models.DayType
import com.github.googelfist.workshedule.domain.models.ScheduleState
import com.github.googelfist.workshedule.domain.usecases.CreateDayTypeUseCase
import com.github.googelfist.workshedule.domain.usecases.DeleteDayTypeUseCase
import com.github.googelfist.workshedule.domain.usecases.EditDayTypeUseCase
import com.github.googelfist.workshedule.domain.usecases.GetCurrentMonthStateUseCase
import com.github.googelfist.workshedule.domain.usecases.GetNextMonthStateUseCase
import com.github.googelfist.workshedule.domain.usecases.GetPreviousMonthStateUseCase
import com.github.googelfist.workshedule.domain.usecases.LoadFirstWorkDateUseCase
import com.github.googelfist.workshedule.domain.usecases.LoadSchedulePatternUseCase
import com.github.googelfist.workshedule.domain.usecases.SaveFirstWorkDateUseCase
import com.github.googelfist.workshedule.domain.usecases.SaveSchedulePatternUseCase
import com.github.googelfist.workshedule.presentation.EventHandler
import com.github.googelfist.workshedule.presentation.schedule.models.ScheduleEvent
import kotlinx.coroutines.launch
import java.time.LocalDate

class ScheduleViewModel(
    private val getPreviousMonthStateUseCase: GetPreviousMonthStateUseCase,
    private val getCurrentMonthStateUseCase: GetCurrentMonthStateUseCase,
    private val getNextMonthStateUseCase: GetNextMonthStateUseCase,
    private val saveFirstWorkDateUseCase: SaveFirstWorkDateUseCase,
    private val loadFirstWorkDateUseCase: LoadFirstWorkDateUseCase,
    private val saveSchedulePatternUseCase: SaveSchedulePatternUseCase,
    private val loadSchedulePatternUseCase: LoadSchedulePatternUseCase,
    private val editDayTypeUseCase: EditDayTypeUseCase,
    private val createDayTypeUseCase: CreateDayTypeUseCase,
    private val deleteDayTypeUseCase: DeleteDayTypeUseCase
) : ViewModel(), EventHandler<ScheduleEvent> {

    private var _scheduleState = MutableLiveData<ScheduleState>()
    val scheduleState: LiveData<ScheduleState>
        get() = _scheduleState

    private var _scheduleTypePattern = MutableLiveData<List<DayType>>()
    val scheduleTypePattern: LiveData<List<DayType>>
        get() = _scheduleTypePattern

    private var _firstWorkDate = MutableLiveData<LocalDate>()
    val firstWorkDate: LiveData<LocalDate>
        get() = _firstWorkDate

    init {
        initMonth()

    }

    // TODO: create separate view model for config fragment
    override fun obtainEvent(event: ScheduleEvent) {
        when (event) {
            ScheduleEvent.GeneratedCurrentMonth -> generatedCurrentMonth()
            ScheduleEvent.GeneratedNextMonth -> generatedNextMonth()
            ScheduleEvent.GeneratedPreviousMonth -> generatedPreviousMonth()
            is ScheduleEvent.RefreshFirstWorkDate -> refreshedFirstWorkDate(event.firstWorkDate)
            is ScheduleEvent.UpdateSchedulePattern -> refreshedSchedulePattern(event.schedulePattern)
            ScheduleEvent.RefreshSchedulePattern -> updateSchedulePattern()

            ScheduleEvent.CreateDayType -> createDayType()
            is ScheduleEvent.EditDayType -> updateDayType(event.position, event.dayType)
            is ScheduleEvent.DeleteDayType -> deleteDayType(event.position)
        }
    }

    private fun initMonth() {
        viewModelScope.launch {
            _scheduleState.value = ScheduleState.LaunchingState
            val scheduleState = getCurrentMonthStateUseCase()
            _scheduleState.value = scheduleState

            val firstDate = loadFirstWorkDateUseCase()
            _firstWorkDate.value = firstDate

            val patternSchedule = loadSchedulePatternUseCase()
            _scheduleTypePattern.value = patternSchedule
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

    private fun refreshedFirstWorkDate(firstWorkDate: String) {
        viewModelScope.launch {
            saveFirstWorkDateUseCase(firstWorkDate)

            val scheduleState = getCurrentMonthStateUseCase()
            _scheduleState.value = scheduleState

            val firstDate = loadFirstWorkDateUseCase()
            _firstWorkDate.value = firstDate
        }
    }

    private fun refreshedSchedulePattern(schedulePattern: List<DayType>) {
        viewModelScope.launch {

            saveSchedulePatternUseCase(schedulePattern)

            val scheduleState = getCurrentMonthStateUseCase()
            _scheduleState.value = scheduleState

            val scheduleTypePattern = loadSchedulePatternUseCase()
            _scheduleTypePattern.value = scheduleTypePattern
        }
    }

    private fun updateSchedulePattern() {
        viewModelScope.launch {

            val scheduleState = getCurrentMonthStateUseCase()
            _scheduleState.value = scheduleState

            val scheduleTypePattern = loadSchedulePatternUseCase()
            _scheduleTypePattern.value = scheduleTypePattern
        }
    }

    private fun createDayType() {
        viewModelScope.launch {
            createDayTypeUseCase()

            val patternSchedule = loadSchedulePatternUseCase()
            _scheduleTypePattern.value = patternSchedule
        }
    }

    private fun updateDayType(position: Int, dayType: DayType) {
        viewModelScope.launch {
            editDayTypeUseCase(position, dayType)

            val patternSchedule = loadSchedulePatternUseCase()
            _scheduleTypePattern.value = patternSchedule
        }
    }

    private fun deleteDayType(position: Int) {
        viewModelScope.launch {
            deleteDayTypeUseCase(position)

            val patternSchedule = loadSchedulePatternUseCase()
            _scheduleTypePattern.value = patternSchedule
        }
    }
}