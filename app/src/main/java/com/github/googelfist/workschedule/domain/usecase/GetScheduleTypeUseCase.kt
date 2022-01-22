package com.github.googelfist.workschedule.domain.usecase

import com.github.googelfist.workschedule.domain.PreferenceRepository

class GetScheduleTypeUseCase(private val preferenceRepository: PreferenceRepository) {

    operator fun invoke(): String {
        return preferenceRepository.loadPreference().scheduleType
    }
}
