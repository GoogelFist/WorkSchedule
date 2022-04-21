package com.github.googelfist.workshedule.domain

import androidx.lifecycle.LiveData
import java.time.LocalDate

interface Repository {
    fun loadFirstWorkDate(): LiveData<LocalDate>
    fun saveFirstWorkDate(firstWorkDate: String)

    fun loadScheduleType(): LiveData<String>
    fun saveScheduleType(scheduleType: String)
}