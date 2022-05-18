package com.github.googelfist.workshedule.domain.usecases

import com.github.googelfist.workshedule.domain.Repository
import javax.inject.Inject

class SaveCurrentConfigIdUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(id: Int) {
        repository.saveCurrentConfigId(id)
    }
}