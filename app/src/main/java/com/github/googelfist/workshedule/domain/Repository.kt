package com.github.googelfist.workshedule.domain

import androidx.lifecycle.LiveData

interface Repository {

    fun generateCurrentMonth(): LiveData<List<Int>>

    fun generateNextMonth(): LiveData<List<Int>>

    fun generatePreviousMonth(): LiveData<List<Int>>

    fun getDateOfChosenMonth(): LiveData<String>
}