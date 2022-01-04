package com.github.googelfist.workshedule.domain.usecase

import com.github.googelfist.workshedule.domain.DateRepository
import com.github.googelfist.workshedule.domain.models.DatePreference

class SaveDatePreferenceUseCase(private val dateRepository: DateRepository) {

    fun saveDatePreference(date: String) {
        val datePreferenceModel = DatePreference(date = date)

        dateRepository.saveDatePreference(datePreferenceModel)
    }
}