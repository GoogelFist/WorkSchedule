package com.github.googelfist.workschedule.data.schedulegenerator.models.defaultday

import com.github.googelfist.workschedule.data.schedulegenerator.models.Day

data class DefaultInActiveDay(
    override val value: Int,
    override val month: Int,
    override val year: Int
) : Day(value, month, year)
