package com.github.googelfist.workschedule.domain.usecase

import com.github.googelfist.workschedule.domain.PreferenceRepository
import javax.inject.Inject

class GetScheduleTypeUseCase @Inject constructor(
    private val preferenceRepository: PreferenceRepository
) {

    operator fun invoke(): String {
        return preferenceRepository.loadPreference().scheduleType
    }
}
