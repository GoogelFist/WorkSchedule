package com.github.googelfist.workshedule.domain.models

interface Day {
    fun getDayValue(): Int
    fun getMonthValue(): Int
    fun getYearValue(): Int
}