package com.github.googelfist.workshedule.domain.schedulesgenerator.daysmapper

import com.github.googelfist.workshedule.domain.models.days.Day

interface DaysMapper {
    fun dayToWorkDay(day: Day): Day

    fun dayToWeekendDay(day: Day): Day
}
