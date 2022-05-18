package com.github.googelfist.workshedule.domain.usecases

import com.github.googelfist.workshedule.domain.Repository
import javax.inject.Inject

class DeleteDayTypeUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(position: Int) {
        repository.deleteDayType(position)
    }
}