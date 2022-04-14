package com.github.googelfist.workshedule.domain.models

interface Month {
    fun getFormattedDate(): String
    fun getDaysList(): List<Day>
}