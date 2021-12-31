package com.github.googelfist.workshedule.domain

data class Day(
    val value: Int,
    val month: Int,
    val year: Int,
    var isActive: Boolean,
    var isToday: Boolean,
    var isWeekend: Boolean,
    var isWork: Boolean
)