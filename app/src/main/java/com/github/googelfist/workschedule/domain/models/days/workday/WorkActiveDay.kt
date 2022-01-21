package com.github.googelfist.workschedule.domain.models.days.workday

import com.github.googelfist.workschedule.domain.models.days.Day

data class WorkActiveDay(
    override val value: Int,
    override val month: Int,
    override val year: Int,
    val isActive: Boolean = true,
    val isToday: Boolean = false,
    val isWork: Boolean = false,
    val isWeekend: Boolean = false
) : Day(value, month, year)
