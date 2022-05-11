package com.github.googelfist.workshedule.domain

import com.github.googelfist.workshedule.domain.models.Day
import com.github.googelfist.workshedule.domain.models.DayType
import java.time.LocalDate

interface Repository {
    suspend fun saveFirstWorkDate(firstWorkDate: String)
    suspend fun loadFirstWorkDate(): LocalDate

    fun putToCache(formattedDate: String, dayList: List<Day>)
    fun getFromCache(formattedDate: String): List<Day>?

    // TODO: temp
    fun loadSchedulePattern(): List<DayType>
    fun saveSchedulePattern(pattern: List<DayType>)
    fun editDayType(position: Int, dayType: DayType)
}