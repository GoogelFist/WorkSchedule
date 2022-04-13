package com.github.googelfist.workschedule.domain.schedulegenerator.daysgenerator.fabric

import com.github.googelfist.workschedule.data.preferencedatasource.PreferenceDataSource
import com.github.googelfist.workschedule.domain.schedulegenerator.daysgenerator.schedulesetup.ScheduleSetup
import com.github.googelfist.workschedule.domain.schedulegenerator.models.Day
import java.time.LocalDate
import javax.inject.Inject

class WorkDaysFabricAdapter @Inject constructor(
    private val workDaysFabric: WorkDaysFabric,
    private val preferenceDataSource: PreferenceDataSource,
    private val scheduleSetup: ScheduleSetup
) : DaysFabric {
    override fun getDay(dateOfMonth: LocalDate, activeDate: LocalDate): Day {
        val firstWorkDay = getFirstWorkDate(preferenceDataSource)
        val actualFirstWorkDay = scheduleSetup.getActualFirstDate(dateOfMonth, firstWorkDay)
        val workSchedule = scheduleSetup.getWorkSchedule(actualFirstWorkDay)

        return workDaysFabric.getWorkDay(
            dateInMonth = dateOfMonth,
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
