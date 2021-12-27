package com.github.googelfist.workshedule.domain

import androidx.lifecycle.LiveData

class GenerateNextMonthUseCase(private val repository: Repository) {

    fun generateNextMonth(): LiveData<List<Day>> {
        return repository.generateNextMonth()
    }
}