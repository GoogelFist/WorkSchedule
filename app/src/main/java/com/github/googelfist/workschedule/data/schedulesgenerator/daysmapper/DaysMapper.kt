package com.github.googelfist.workschedule.data.schedulesgenerator.daysmapper

import com.github.googelfist.workschedule.domain.models.days.Day

interface DaysMapper {
    fun dayToWorkDay(day: Day): Day

    fun dayToWeekendDay(day: Day): Day
}
