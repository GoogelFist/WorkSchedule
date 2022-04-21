package com.github.googelfist.workshedule.data.datasource

import androidx.lifecycle.LiveData
import java.time.LocalDate

interface LocalDataSource {
    fun loadFirstWorkDate(): LiveData<LocalDate>
    fun saveFirstWorkDate(firstWorkDate: String)

    fun loadScheduleType(): LiveData<String>
    fun saveScheduleType(scheduleType: String)
}