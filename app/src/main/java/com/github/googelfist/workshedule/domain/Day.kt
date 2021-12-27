package com.github.googelfist.workshedule.domain

data class Day(
    val value: Int,
    val month: Int,
    val isWork: Boolean,
    val isWeekend: Boolean,
    val isActive: Boolean
)