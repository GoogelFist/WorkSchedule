package com.github.googelfist.workshedule.domain.models.days

data class InActiveDay(
    override val value: Int,
    override val month: Int,
    override val year: Int,
    val isWeekend: Boolean = false,
    val isWork: Boolean = false
) : Day(value, month, year)
