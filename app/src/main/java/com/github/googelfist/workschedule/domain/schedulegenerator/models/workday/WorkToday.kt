package com.github.googelfist.workschedule.domain.schedulegenerator.models.workday

import com.github.googelfist.workschedule.domain.schedulegenerator.models.Day

data class WorkToday(
    override val day: Int,
    override val month: Int,
    override val year: Int,
    val isActive: Boolean = true,
    val isToday: Boolean = true,
    val isWork: Boolean = false,
    val isWeekend: Boolean = false
) : Day(day, month, year)
