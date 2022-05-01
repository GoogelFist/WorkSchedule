package com.github.googelfist.workshedule.domain.usecases

import com.github.googelfist.workshedule.domain.Repository
import com.github.googelfist.workshedule.domain.models.ScheduleTypeState
import javax.inject.Inject

class LoadScheduleTypeUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(): ScheduleTypeState {
        return repository.loadScheduleType()
    }
}