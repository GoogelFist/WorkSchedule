package com.github.googelfist.workshedule.domain.models

import java.time.LocalDate

sealed class ScheduleTypeState {
    data class Default(val type: String = DEFAULT_TYPE) : ScheduleTypeState()

    data class TwoInTwo(
        val firstWorkDate: LocalDate,
        val dayCycleStep: Long,
        val type: String = TWO_IN_TWO_TYPE
    ) : ScheduleTypeState()

    companion object {
        private const val TWO_IN_TWO_TYPE = "2/2"
        private const val DEFAULT_TYPE = "Default"
    }
}