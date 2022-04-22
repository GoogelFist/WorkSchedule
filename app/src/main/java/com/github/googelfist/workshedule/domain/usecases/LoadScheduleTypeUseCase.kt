package com.github.googelfist.workshedule.domain.usecases

import com.github.googelfist.workshedule.domain.Repository
import com.github.googelfist.workshedule.domain.ScheduleType
import javax.inject.Inject

class LoadScheduleTypeUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(): ScheduleType {
        return repository.loadScheduleType()
    }
}