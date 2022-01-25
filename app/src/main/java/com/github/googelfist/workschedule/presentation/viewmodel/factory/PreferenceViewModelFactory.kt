package com.github.googelfist.workschedule.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.googelfist.workschedule.domain.usecase.GetScheduleTypeUseCase
import com.github.googelfist.workschedule.presentation.viewmodel.PreferenceViewModel
import javax.inject.Inject

class PreferenceViewModelFactory @Inject constructor(
    private val getScheduleTypeUseCase: GetScheduleTypeUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PreferenceViewModel(getScheduleTypeUseCase) as T
    }
}
