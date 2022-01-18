package com.github.googelfist.workschedule.data.schedulesgenerator.work.scheduletype

import java.time.LocalDate

interface ScheduleType {

    fun getWorkSchedule(date: LocalDate): Set<LocalDate>
}
