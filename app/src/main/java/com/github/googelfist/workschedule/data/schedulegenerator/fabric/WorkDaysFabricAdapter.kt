package com.github.googelfist.workschedule.data.schedulegenerator.fabric

import com.github.googelfist.workschedule.data.schedulegenerator.models.Day
import com.github.googelfist.workschedule.data.schedulegenerator.schedulesetup.ScheduleSetup
import com.github.googelfist.workschedule.domain.PreferenceRepository
import java.time.LocalDate

class WorkDaysFabricAdapter(
    private val workDaysFabric: WorkDaysFabric,
    private val preferenceRepository: PreferenceRepository,
    private val scheduleSetup: ScheduleSetup
) : DaysFabric {
    override fun getDay(dateInMonth: LocalDate, activeDate: LocalDate): Day {
        return workDaysFabric.getWorkDay(
            dateInMonth = dateInMonth,
            activeDate = activeDate,
            preferenceRepository = preferenceRepository,
            scheduleSetup = scheduleSetup
        )
    }
}
