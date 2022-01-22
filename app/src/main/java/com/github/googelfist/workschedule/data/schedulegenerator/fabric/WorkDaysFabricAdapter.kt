package com.github.googelfist.workschedule.data.schedulegenerator.fabric

import com.github.googelfist.workschedule.data.schedulegenerator.scheduletype.ScheduleSetup
import com.github.googelfist.workschedule.domain.PreferenceRepository
import com.github.googelfist.workschedule.domain.models.days.Day
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
