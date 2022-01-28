package com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.models.workday

import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.models.Day

data class WorkActiveDay(
    override val day: Int,
    override val month: Int,
    override val year: Int,
    val isActive: Boolean = true,
    val isToday: Boolean = false,
    val isWork: Boolean = false,
    val isWeekend: Boolean = false
) : Day(day, month, year)
