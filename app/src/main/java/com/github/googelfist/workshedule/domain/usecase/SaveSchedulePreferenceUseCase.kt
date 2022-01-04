package com.github.googelfist.workshedule.domain.usecase

import com.github.googelfist.workshedule.domain.DateRepository
import com.github.googelfist.workshedule.domain.models.SchedulePreference

class SaveSchedulePreferenceUseCase(private val dateRepository: DateRepository) {

    fun saveSchedulePreference(schedule: String) {
        val schedulePreferenceModel = SchedulePreference(schedule = schedule)

        dateRepository.saveSchedulePreference(schedulePreferenceModel)
    }
}