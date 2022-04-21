package com.github.googelfist.workshedule.domain.usecases

import com.github.googelfist.workshedule.domain.Repository
import javax.inject.Inject

class SaveFirstWorkDateUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(firstWorkDate: String) {
        repository.saveFirstWorkDate(firstWorkDate)
    }
}