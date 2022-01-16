package com.github.googelfist.workschedule.domain.models.days

data class InActiveDay(
    override val value: Int,
    override val month: Int,
    override val year: Int,
    val isWork: Boolean = false,
    val isWeekend: Boolean = false
) : Day(value, month, year)
