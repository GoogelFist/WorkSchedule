package com.github.googelfist.workshedule.presentation.screens.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.googelfist.workshedule.domain.usecases.GetCurrentMonthStateUseCase
import com.github.googelfist.workshedule.domain.usecases.GetNextMonthStateUseCase
import com.github.googelfist.workshedule.domain.usecases.GetPreviousMonthStateUseCase
import javax.inject.Inject

class ScheduleViewModelFactory @Inject constructor(
    private val getPreviousMonthStateUseCase: GetPreviousMonthStateUseCase,
    private val getCurrentMonthStateUseCase: GetCurrentMonthStateUseCase,
    private val getNextMonthStateUseCase: GetNextMonthStateUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScheduleViewModel::class.java)) {
            return ScheduleViewModel(
                getPreviousMonthStateUseCase = getPreviousMonthStateUseCase,
                getCurrentMonthStateUseCase = getCurrentMonthStateUseCase,
                getNextMonthStateUseCase = getNextMonthStateUseCase
            ) as T
        }
        throw RuntimeException("Unknown class name")
    }
}