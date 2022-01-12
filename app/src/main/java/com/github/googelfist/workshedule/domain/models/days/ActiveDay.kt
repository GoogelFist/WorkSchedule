package com.github.googelfist.workshedule.domain.models.days

data class ActiveDay(
    override val value: Int,
    override val month: Int,
    override val year: Int,
    val isActive: Boolean = true,
    val isToday: Boolean = false,
    val isWeekend: Boolean = false,
    val isWork: Boolean = false
) : Day(value, month, year)
