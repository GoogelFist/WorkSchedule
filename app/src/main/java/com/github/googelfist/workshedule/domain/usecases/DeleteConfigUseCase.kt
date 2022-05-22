package com.github.googelfist.workshedule.domain.usecases

import com.github.googelfist.workshedule.domain.Repository
import javax.inject.Inject

class DeleteConfigUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(id: Int) {
        return repository.deleteConfig(id)
    }
}