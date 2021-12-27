package com.github.googelfist.workshedule.domain

data class Day(
    val value: Int,
    val month: Int,
    val isActive: Boolean,
    val isToday: Boolean,
    val isWeekend: Boolean,
    val isWork: Boolean
)