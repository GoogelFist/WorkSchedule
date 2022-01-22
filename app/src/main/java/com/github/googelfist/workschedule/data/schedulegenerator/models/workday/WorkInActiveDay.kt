package com.github.googelfist.workschedule.data.schedulegenerator.models.workday

import com.github.googelfist.workschedule.data.schedulegenerator.models.Day

data class WorkInActiveDay(
    override val value: Int,
    override val month: Int,
    override val year: Int,
    val isWork: Boolean = false,
    val isWeekend: Boolean = false
) : Day(value, month, year)
