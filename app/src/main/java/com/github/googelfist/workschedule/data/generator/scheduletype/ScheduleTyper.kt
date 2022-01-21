package com.github.googelfist.workschedule.data.generator.scheduletype

import java.time.LocalDate

interface ScheduleTyper {

    fun getWorkSchedule(date: LocalDate): Set<LocalDate>

    fun getActualFirstDate(activeDate: LocalDate, firstWorkDate: LocalDate): LocalDate
}
