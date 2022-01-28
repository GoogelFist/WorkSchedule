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
        val firstWorkDay = getFirstWorkDate(preferenceDataSource)
        val actualFirstWorkDay = scheduleSetup.getActualFirstDate(dateInMonth, firstWorkDay)
        val workSchedule = scheduleSetup.getWorkSchedule(actualFirstWorkDay)

        return workDaysFabric.getWorkDay(
            dateInMonth = dateInMonth,
            activeDate = activeDate,
            firstWorkDay = firstWorkDay,
            actualFirstWorkDay = actualFirstWorkDay,
            workSchedule = workSchedule
        )
    }

    private fun getFirstWorkDate(preferenceDataSource: PreferenceDataSource): LocalDate {
        val firstWorkDatePreference = preferenceDataSource.loadPreference().firstWorkDate
        return LocalDate.parse(firstWorkDatePreference)
    }
}
