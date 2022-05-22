package com.github.googelfist.workshedule.domain.usecases

import com.github.googelfist.workshedule.domain.Repository
import com.github.googelfist.workshedule.domain.models.Config
import javax.inject.Inject

class LoadConfigListUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(): List<Config> {
        return repository.loadConfigList()
    }
}