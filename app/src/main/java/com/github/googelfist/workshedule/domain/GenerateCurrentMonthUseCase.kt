package com.github.googelfist.workshedule.domain

import androidx.lifecycle.LiveData

class GenerateCurrentMonthUseCase(private val repository: Repository) {

    fun generateCurrentMonth(): LiveData<List<Int>> {
        return repository.generateCurrentMonth()
    }
}