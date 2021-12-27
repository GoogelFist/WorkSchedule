package com.github.googelfist.workshedule.domain

import androidx.lifecycle.LiveData

class GetDateOfChosenMonthUseCase(private val repository: Repository) {

    fun getDateOfChosenMonth(): LiveData<String> {
        return repository.getDateOfChosenMonth()
    }
}