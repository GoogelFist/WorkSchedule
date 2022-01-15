package com.github.googelfist.workschedule.domain.usecase

import com.github.googelfist.workschedule.domain.PreferenceRepository
import com.github.googelfist.workschedule.domain.models.PreferencesModel

class SavePreferenceUseCase(private val preferenceRepository: PreferenceRepository) {

    operator fun invoke(preference: PreferencesModel) {

        preferenceRepository.savePreference(preference)
    }
}
