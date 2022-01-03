package com.github.googelfist.workshedule.presentation.settings

import androidx.lifecycle.ViewModel
import com.github.googelfist.workshedule.data.DateRepositoryImpl
import com.github.googelfist.workshedule.domain.SaveDatePreferenceUseCase
import com.github.googelfist.workshedule.domain.SaveSchedulePreferenceUseCase

class SettingsViewModel : ViewModel() {
    private val dateRepository = DateRepositoryImpl()

    private val saveDatePreferenceUseCase = SaveDatePreferenceUseCase(dateRepository)
    private val saveScheduleDateUseCase = SaveSchedulePreferenceUseCase(dateRepository)

    fun saveSchedulePreference(schedule: String) {
        saveScheduleDateUseCase.saveSchedulePreference(schedule)
    }

    fun saveDatePreference(date: String) {
        saveDatePreferenceUseCase.saveDatePreference(date)
    }
}