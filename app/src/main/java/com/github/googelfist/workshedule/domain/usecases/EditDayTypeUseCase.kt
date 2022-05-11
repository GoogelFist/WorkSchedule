package com.github.googelfist.workshedule.domain.usecases

import com.github.googelfist.workshedule.domain.Repository
import com.github.googelfist.workshedule.domain.models.DayType
import javax.inject.Inject

class EditDayTypeUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(position: Int, dayType: DayType) {
        repository.editDayType(position, dayType)
    }
}