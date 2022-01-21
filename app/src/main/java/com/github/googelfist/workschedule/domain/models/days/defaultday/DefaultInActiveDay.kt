package com.github.googelfist.workschedule.domain.models.days.defaultday

import com.github.googelfist.workschedule.domain.models.days.Day

data class DefaultInActiveDay(
    override val value: Int,
    override val month: Int,
    override val year: Int
) : Day(value, month, year)
