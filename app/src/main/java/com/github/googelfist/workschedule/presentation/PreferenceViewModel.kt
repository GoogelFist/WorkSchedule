package com.github.googelfist.workschedule.presentation

import androidx.lifecycle.ViewModel
import com.github.googelfist.workschedule.domain.usecase.GetScheduleTypeUseCase

class PreferenceViewModel(getScheduleTypeUseCase: GetScheduleTypeUseCase) : ViewModel() {

    val scheduleTypeLD = getScheduleTypeUseCase.invoke()
}
