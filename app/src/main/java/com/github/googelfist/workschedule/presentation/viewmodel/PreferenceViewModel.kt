package com.github.googelfist.workschedule.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.googelfist.workschedule.domain.usecase.GetScheduleTypeUseCase

class PreferenceViewModel(private val getScheduleTypeUseCase: GetScheduleTypeUseCase) :
    ViewModel() {

    private var _scheduleTypeLD = MutableLiveData<String>()
    private val scheduleTypeLD: LiveData<String>
        get() = _scheduleTypeLD

    fun onScheduleTypeChange(): LiveData<String> {
        val scheduleType = getScheduleTypeUseCase.invoke()
        _scheduleTypeLD.value = scheduleType
        return scheduleTypeLD
    }
}
