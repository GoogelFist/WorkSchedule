package com.github.googelfist.workschedule.domain.models.days

data class InActiveDay(
    override val value: Int,
    override val month: Int,
    override val year: Int,
    val isWork: Boolean = false,
    val isWeekend: Boolean = true
) : Day(value, month, year)
