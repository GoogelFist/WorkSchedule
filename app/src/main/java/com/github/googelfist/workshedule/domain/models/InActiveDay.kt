package com.github.googelfist.workshedule.domain.models

data class InActiveDay(
    override val value: Int,
    override val month: Int,
    override val year: Int,
    override val isActive: Boolean = false,
) : Day(value, month, year)