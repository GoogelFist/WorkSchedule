package com.github.googelfist.workshedule.domain.monthgenerator

sealed class DayType {
    object DefaultDay : DayType()
    object WorkDay : DayType()
    object WorkNight : DayType()
    object WeekendDaySleepOff : DayType()
    object WeekendDay : DayType()
}