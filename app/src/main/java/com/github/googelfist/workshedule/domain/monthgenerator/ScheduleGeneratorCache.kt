package com.github.googelfist.workshedule.domain.monthgenerator

import com.github.googelfist.workshedule.domain.models.Day
import javax.inject.Inject

class ScheduleGeneratorCache @Inject constructor() {

    private val cache = mutableMapOf<String, List<Day>>()

    fun putToCache(formattedDate: String, dayList: List<Day>) {
        cache[formattedDate] = dayList
    }

    fun getFromCache(formattedDate: String): List<Day>? {
        return cache[formattedDate]
    }

    fun clearCache() {
        cache.clear()
    }
}