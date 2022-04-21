package com.github.googelfist.workshedule.domain.usecases.twointwo

import androidx.lifecycle.LiveData
import com.github.googelfist.workshedule.domain.Repository
import java.time.LocalDate
import javax.inject.Inject

class LoadFirstWorkDateUseCase @Inject constructor(private val repository: Repository) {
    operator fun invoke(): LiveData<LocalDate> {
        return repository.loadFirstWorkDate()
    }
}