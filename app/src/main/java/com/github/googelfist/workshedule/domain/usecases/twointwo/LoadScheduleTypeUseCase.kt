package com.github.googelfist.workshedule.domain.usecases.twointwo

import androidx.lifecycle.LiveData
import com.github.googelfist.workshedule.domain.Repository
import javax.inject.Inject

class LoadScheduleTypeUseCase @Inject constructor(private val repository: Repository) {
    operator fun invoke(): LiveData<String> {
        return repository.loadScheduleType()
    }
}