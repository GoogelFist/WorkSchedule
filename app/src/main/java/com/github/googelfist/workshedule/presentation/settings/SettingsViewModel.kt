package com.github.googelfist.workshedule.presentation.settings

import androidx.lifecycle.ViewModel
import com.github.googelfist.workshedule.data.DateRepositoryImpl
import com.github.googelfist.workshedule.domain.SavePreferenceUseCase

class SettingsViewModel : ViewModel() {
    private val dateRepository = DateRepositoryImpl()

    private val savePreferenceUseCase = SavePreferenceUseCase(dateRepository)

    fun savePreference(preference: Preference) {
        savePreferenceUseCase.savePreference(preference)
    }
}