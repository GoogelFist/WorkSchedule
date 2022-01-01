package com.github.googelfist.workshedule.domain

import com.github.googelfist.workshedule.presentation.settings.Preference

class SavePreferenceUseCase(private val dateRepository: DateRepository) {

    fun savePreference(preference: Preference) {
        dateRepository.savePreference(preference)
    }
}