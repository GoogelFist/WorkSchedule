package com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.fabric

import com.github.googelfist.workschedule.data.preferencedatasource.PreferenceDataSource
import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.models.Day
import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.schedulesetup.ScheduleSetup
import java.time.LocalDate

interface WorkDaysFabric {
    fun getWorkDay(
        dateInMonth: LocalDate,
        activeDate: LocalDate,
        preferenceDataSource: PreferenceDataSource,
        scheduleSetup: ScheduleSetup
    ): Day
}
