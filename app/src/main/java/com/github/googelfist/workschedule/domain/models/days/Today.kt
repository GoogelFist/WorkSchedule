package com.github.googelfist.workschedule.domain.models.days

data class Today(
    override val value: Int,
    override val month: Int,
    override val year: Int,
    val isActive: Boolean = true,
    val isToday: Boolean = true,
    val isWork: Boolean = false,
    val isWeekend: Boolean = true
) : Day(value, month, year)
