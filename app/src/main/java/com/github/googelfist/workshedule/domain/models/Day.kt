package com.github.googelfist.workshedule.domain.models

open class Day(
    open val value: Int,
    open val month: Int,
    open val year: Int,
    open val isActive: Boolean = false,
    open val isToday: Boolean = false,
    open var isWeekend: Boolean = false,
    open var isWork: Boolean = false
)