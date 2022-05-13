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
import java.time.LocalDate

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

    private var _firstWorkDate = MutableLiveData<LocalDate>()
    val firstWorkDate: LiveData<LocalDate>
        get() = _firstWorkDate

    init {
        initMonth()
    }

    override fun obtainEvent(event: ConfigEvent) {
        when (event) {
            is ConfigEvent.RefreshFirstWorkDate -> refreshedFirstWorkDate(event.firstWorkDate)
            is ConfigEvent.UpdateSchedulePattern -> refreshedSchedulePattern(event.schedulePattern)
            ConfigEvent.RefreshSchedulePattern -> updateSchedulePattern()

            ConfigEvent.CreateDayType -> createDayType()
            is ConfigEvent.EditDayType -> updateDayType(event.position, event.dayType)
            is ConfigEvent.DeleteDayType -> deleteDayType(event.position)
        }
    }

    private fun initMonth() {
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

    private fun refreshedSchedulePattern(schedulePattern: List<DayType>) {
        viewModelScope.launch {

            saveSchedulePatternUseCase(schedulePattern)

            val scheduleTypePattern = loadSchedulePatternUseCase()
            _scheduleTypePattern.value = scheduleTypePattern
        }
    }

    private fun updateSchedulePattern() {
        viewModelScope.launch {

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