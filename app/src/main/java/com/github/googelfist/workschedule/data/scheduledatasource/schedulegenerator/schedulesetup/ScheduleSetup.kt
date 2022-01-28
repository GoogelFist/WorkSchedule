package com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.schedulesetup

import java.time.LocalDate

interface ScheduleSetup {

    fun getWorkSchedule(date: LocalDate): Set<LocalDate>

    fun getActualFirstDate(activeDate: LocalDate, firstWorkDate: LocalDate): LocalDate
}
