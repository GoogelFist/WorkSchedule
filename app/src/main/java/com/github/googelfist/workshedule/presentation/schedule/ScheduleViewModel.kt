package com.github.googelfist.workshedule.presentation.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.googelfist.workshedule.domain.models.month.Month
import com.github.googelfist.workshedule.domain.usecases.GenerateCurrentMonthUseCase
import com.github.googelfist.workshedule.domain.usecases.GenerateNextMonthUseCase
import com.github.googelfist.workshedule.domain.usecases.GeneratePreviousMonthUseCase
import com.github.googelfist.workshedule.domain.usecases.SaveFirstWorkDateUseCase
import com.github.googelfist.workshedule.domain.usecases.SaveScheduleTypeUseCase
import kotlinx.coroutines.launch

class ScheduleViewModel(
    private val generatePreviousMonthUseCase: GeneratePreviousMonthUseCase,
    private val generateCurrentMonthUseCase: GenerateCurrentMonthUseCase,
    private val generateNextMonthUseCase: GenerateNextMonthUseCase,
    private val saveFirstWorkDateUseCase: SaveFirstWorkDateUseCase,
    private val saveScheduleTypeUseCase: SaveScheduleTypeUseCase,
) : ViewModel() {

    private var _month = MutableLiveData<Month>()
    val month: LiveData<Month>
        get() = _month

    init {
        onGenerateCurrentMonth()
    }

    fun onGenerateCurrentMonth() {
        viewModelScope.launch {
            val month = generateCurrentMonthUseCase()
            _month.value = month
        }
    }

    fun onGeneratePreviousMonth() {
        viewModelScope.launch {
            val month = generatePreviousMonthUseCase()
            _month.value = month
        }
    }

    fun onGenerateNextMonth() {
        viewModelScope.launch {
            val month = generateNextMonthUseCase()
            _month.value = month
        }
    }

    fun onRefreshScheduleType(scheduleType: String) {
        viewModelScope.launch {
            saveScheduleTypeUseCase(scheduleType)
            val month = generateCurrentMonthUseCase()
            _month.value = month
        }
    }

    fun onRefreshFirstWorkDate(firstWorkDate: String) {
        viewModelScope.launch {
            saveFirstWorkDateUseCase(firstWorkDate)
            val month = generateCurrentMonthUseCase()
            _month.value = month
        }
    }
}