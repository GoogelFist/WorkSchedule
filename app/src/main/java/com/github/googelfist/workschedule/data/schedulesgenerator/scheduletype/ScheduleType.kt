package com.github.googelfist.workschedule.data.schedulesgenerator.scheduletype

import java.time.LocalDate

interface ScheduleType {

    fun getWorkSchedule(date: LocalDate): Set<LocalDate>
}
