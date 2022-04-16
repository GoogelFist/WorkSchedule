package com.github.googelfist.workshedule.domain.models.month

import com.github.googelfist.workshedule.domain.models.day.Day

interface Month {
    fun getFormattedDate(): String
    fun getDaysList(): List<Day>
}