package com.github.googelfist.workshedule.domain.models.month

import com.github.googelfist.workshedule.domain.models.day.Day

data class DefaultMonth(private val formattedDate: String, private val daysList: List<Day>) :
    Month {
    override fun getFormattedDate(): String {
        return formattedDate
    }

    override fun getDaysList(): List<Day> {
        return daysList
    }
}
