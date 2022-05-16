package com.github.googelfist.workshedule.domain.models

import java.time.LocalDate

data class GenerateConfig(
    val id: Int,
    val firstWorkDate: LocalDate,
    val schedulePattern: List<DayType>
)