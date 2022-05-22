package com.github.googelfist.workshedule.domain.models

data class Config(
    val id: Int,
    val configName: String?,
    val isCurrent: Boolean = false
)