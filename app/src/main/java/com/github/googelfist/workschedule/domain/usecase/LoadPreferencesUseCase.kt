package com.github.googelfist.workschedule.domain.usecase

import com.github.googelfist.workschedule.domain.PreferenceRepository
import com.github.googelfist.workschedule.domain.models.PreferencesModel

class LoadPreferencesUseCase(private val preferenceRepository: PreferenceRepository) {

    operator fun invoke(): PreferencesModel {
        return preferenceRepository.loadPreference()
    }
}
