package com.github.googelfist.workshedule.domain.models

data class ActiveDay(
    override val value: Int,
    override val month: Int,
    override val year: Int,
    override val isActive: Boolean = true,
    override val isToday: Boolean = false
) : Day(value, month, year)