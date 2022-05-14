package com.github.googelfist.workshedule.domain.usecases

import com.github.googelfist.workshedule.domain.Repository
import com.github.googelfist.workshedule.domain.models.ScheduleConfig
import javax.inject.Inject

class LoadScheduleConfigUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(): ScheduleConfig {
        return repository.loadScheduleConfig()
    }
}