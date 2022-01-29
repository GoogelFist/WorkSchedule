package com.github.googelfist.workschedule.domain.schedulegenerator.models.defaultday

import com.github.googelfist.workschedule.domain.schedulegenerator.models.Day

data class DefaultToday(
    override val day: Int,
    override val month: Int,
    override val year: Int,
    val isActive: Boolean = true,
    val isToday: Boolean = true
) : Day(day, month, year)
