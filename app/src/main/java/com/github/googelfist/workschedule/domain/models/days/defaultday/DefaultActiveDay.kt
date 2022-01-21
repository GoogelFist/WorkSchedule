package com.github.googelfist.workschedule.domain.models.days.defaultday

import com.github.googelfist.workschedule.domain.models.days.Day

data class DefaultActiveDay(
    override val value: Int,
    override val month: Int,
    override val year: Int,
    val isActive: Boolean = true,
    val isToday: Boolean = false
) : Day(value, month, year)
