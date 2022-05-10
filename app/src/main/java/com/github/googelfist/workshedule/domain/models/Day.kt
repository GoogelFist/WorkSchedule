package com.github.googelfist.workshedule.domain.models

data class Day(
    val day: Int,
    val month: Int,
    val year: Int,
    val today: Boolean,
    val currentMonth: Boolean,
    val backgroundColor: String,
    val title: String
)