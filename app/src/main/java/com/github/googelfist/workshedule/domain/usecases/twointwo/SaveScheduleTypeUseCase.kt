package com.github.googelfist.workshedule.domain.usecases.twointwo

import com.github.googelfist.workshedule.domain.Repository
import javax.inject.Inject

class SaveScheduleTypeUseCase @Inject constructor(private val repository: Repository) {
    operator fun invoke(scheduleType: String) {
        repository.saveScheduleType(scheduleType)
    }
}