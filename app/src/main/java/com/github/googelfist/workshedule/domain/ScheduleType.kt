package com.github.googelfist.workshedule.domain

import java.time.LocalDate

sealed class ScheduleType {
    data class Default(val type: String = DEFAULT_TYPE) : ScheduleType()

    data class TwoInTwo(
        val firstWorkDate: LocalDate,
        val dayCycleStep: Long,
        val type: String = TWO_IN_TWO_TYPE
    ) : ScheduleType()

    companion object {
        private const val TWO_IN_TWO_TYPE = "2/2"
        private const val DEFAULT_TYPE = "Default"
    }
}