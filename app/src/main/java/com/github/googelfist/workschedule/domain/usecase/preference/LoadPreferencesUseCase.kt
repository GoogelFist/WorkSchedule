package com.github.googelfist.workschedule.domain.usecase.preference

import com.github.googelfist.workschedule.domain.PreferenceRepository
import com.github.googelfist.workschedule.domain.models.PreferencesDTO

class LoadPreferencesUseCase(private val preferenceRepository: PreferenceRepository) {

    operator fun invoke(): PreferencesDTO {
        return preferenceRepository.loadPreference()
    }
}
