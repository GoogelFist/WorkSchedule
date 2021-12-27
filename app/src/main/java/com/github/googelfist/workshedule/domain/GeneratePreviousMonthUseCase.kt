package com.github.googelfist.workshedule.domain

import androidx.lifecycle.LiveData

class GeneratePreviousMonthUseCase(private val repository: Repository) {

    fun generatePreviousMonth(): LiveData<List<Day>> {
        return repository.generatePreviousMonth()
    }
}