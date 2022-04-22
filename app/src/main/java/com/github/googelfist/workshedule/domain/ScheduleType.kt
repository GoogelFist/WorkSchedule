package com.github.googelfist.workshedule.domain

import java.time.LocalDate

sealed class ScheduleType {
    object Default : ScheduleType()
    data class TwoInTwo(val firstWorkDate: LocalDate, val dayCycleStep: Long) : ScheduleType()
}
