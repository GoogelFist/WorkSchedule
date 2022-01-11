package com.github.googelfist.workshedule.domain.models

data class Day(
    val value: Int,
    val month: Int,
    val year: Int,
    val isActive: Boolean = false,
    val isToday: Boolean = false,
    val isWeekend: Boolean = false,
    val isWork: Boolean = false
)