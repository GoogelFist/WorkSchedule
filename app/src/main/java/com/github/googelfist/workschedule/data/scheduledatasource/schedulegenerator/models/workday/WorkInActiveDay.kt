package com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.models.workday

import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.models.Day

data class WorkInActiveDay(
    override val day: Int,
    override val month: Int,
    override val year: Int,
    val isWork: Boolean = false,
    val isWeekend: Boolean = false
) : Day(day, month, year)
