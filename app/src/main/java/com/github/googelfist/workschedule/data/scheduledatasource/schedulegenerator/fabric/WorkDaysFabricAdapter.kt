package com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.fabric

import com.github.googelfist.workschedule.data.preferencedatasource.PreferenceDataSource
import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.models.Day
import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.schedulesetup.ScheduleSetup
import java.time.LocalDate
import javax.inject.Inject

class WorkDaysFabricAdapter @Inject constructor(
    private val workDaysFabric: WorkDaysFabric,
    private val preferenceDataSource: PreferenceDataSource,
    private val scheduleSetup: ScheduleSetup
) : DaysFabric {
    override fun getDay(dateInMonth: LocalDate, activeDate: LocalDate): Day {
        return workDaysFabric.getWorkDay(
            dateInMonth = dateInMonth,
            activeDate = activeDate,
            preferenceDataSource = preferenceDataSource,
            scheduleSetup = scheduleSetup
        )
    }
}
