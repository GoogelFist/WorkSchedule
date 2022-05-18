package com.github.googelfist.workshedule.domain.usecases

import com.github.googelfist.workshedule.domain.Repository
import javax.inject.Inject

class SavePatternNameUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(name: String) {
        repository.saveConfigName(name)
    }
}