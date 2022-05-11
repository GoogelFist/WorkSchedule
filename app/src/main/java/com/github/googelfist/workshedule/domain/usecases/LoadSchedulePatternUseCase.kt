package com.github.googelfist.workshedule.domain.usecases

import com.github.googelfist.workshedule.domain.Repository
import com.github.googelfist.workshedule.domain.models.DayType
import javax.inject.Inject

class LoadSchedulePatternUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(): List<DayType> {
        return repository.loadSchedulePattern()
    }
}