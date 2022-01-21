package com.github.googelfist.workschedule.presentation

import androidx.lifecycle.ViewModel
import com.github.googelfist.workschedule.domain.usecase.preference.LoadPreferencesUseCase

class PreferenceViewModel(loadPreferencesUseCase: LoadPreferencesUseCase) : ViewModel() {

    val preferencesLD = loadPreferencesUseCase.invoke()
}
