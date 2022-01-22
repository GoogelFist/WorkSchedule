package com.github.googelfist.workschedule.data.schedulegenerator.fabric

import com.github.googelfist.workschedule.data.schedulegenerator.scheduletype.ScheduleSetup
import com.github.googelfist.workschedule.domain.PreferenceRepository
import com.github.googelfist.workschedule.domain.models.Day
import java.time.LocalDate

interface WorkDaysFabric {
    fun getWorkDay(
        dateInMonth: LocalDate,
        activeDate: LocalDate,
        preferenceRepository: PreferenceRepository,
        scheduleSetup: ScheduleSetup
    ): Day
}
