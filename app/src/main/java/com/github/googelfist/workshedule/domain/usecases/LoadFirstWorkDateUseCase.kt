package com.github.googelfist.workshedule.domain.usecases

import com.github.googelfist.workshedule.domain.Repository
import java.time.LocalDate
import javax.inject.Inject

class LoadFirstWorkDateUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(): LocalDate {
        return repository.loadFirstWorkDate()
    }
}