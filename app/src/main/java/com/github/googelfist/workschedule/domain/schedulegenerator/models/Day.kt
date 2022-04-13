package com.github.googelfist.workschedule.domain.schedulegenerator.models

interface Day {
    fun getDayValue(): Int
    fun getMonthValue(): Int
    fun getYearValue(): Int
}