package com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.models.defaultday

import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.models.Day

data class DefaultActiveDay(
    override val value: Int,
    override val month: Int,
    override val year: Int,
    val isActive: Boolean = true,
    val isToday: Boolean = false
) : Day(value, month, year)
