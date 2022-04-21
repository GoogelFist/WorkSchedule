package com.github.googelfist.workshedule.domain.usecases.twointwo

import com.github.googelfist.workshedule.domain.Repository
import javax.inject.Inject

class SaveFirstWorkDateUseCase @Inject constructor(private val repository: Repository) {
    operator fun invoke(firstWorkDate: String) {
        repository.saveFirstWorkDate(firstWorkDate)
    }
}