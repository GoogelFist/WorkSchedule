package com.github.googelfist.workshedule.domain

import androidx.lifecycle.LiveData

interface Repository {

    fun generateCurrentMonth(): LiveData<List<Day>>

    fun generateNextMonth(): LiveData<List<Day>>

    fun generatePreviousMonth(): LiveData<List<Day>>

    fun getDateOfChosenMonth(): LiveData<String>
}