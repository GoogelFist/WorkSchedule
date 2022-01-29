package com.github.googelfist.workschedule.domain.schedulegenerator.models.defaultday

import com.github.googelfist.workschedule.domain.schedulegenerator.models.Day

data class DefaultInActiveDay(
    override val day: Int,
    override val month: Int,
    override val year: Int
) : Day(day, month, year)
