package com.github.googelfist.workshedule.domain.models

import com.github.googelfist.workshedule.domain.models.day.Day

data class ScheduleState(val formattedDate: String, val dayList: List<Day>)
