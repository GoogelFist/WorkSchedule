package com.github.googelfist.workshedule.domain.monthgenerator

sealed class DayType {
    data class DefaultDay(val backgroundColor: String, val title: String) : DayType()
    data class WorkDay(val backgroundColor: String, val title: String) : DayType()
    data class WorkNight(val backgroundColor: String, val title: String) : DayType()
    data class WeekendDaySleepOff(val backgroundColor: String, val title: String) : DayType()
    data class WeekendDay(val backgroundColor: String, val title: String) : DayType()
}