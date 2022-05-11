package com.github.googelfist.workshedule.domain.usecases

import com.github.googelfist.workshedule.domain.Repository
import com.github.googelfist.workshedule.domain.models.DayType
import javax.inject.Inject

class SaveSchedulePatternUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(schedulePattern: List<DayType>) {
        repository.saveSchedulePattern(schedulePattern)
    }
}