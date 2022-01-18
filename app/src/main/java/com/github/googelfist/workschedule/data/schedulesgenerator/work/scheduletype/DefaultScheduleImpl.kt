package com.github.googelfist.workschedule.data.schedulesgenerator.work.scheduletype

import java.time.LocalDate

class DefaultScheduleImpl : ScheduleType {

    override fun getWorkSchedule(date: LocalDate): Set<LocalDate> {
        return emptySet()
    }
}
