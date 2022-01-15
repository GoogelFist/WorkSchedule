package com.github.googelfist.workschedule.domain.usecase

import com.github.googelfist.workschedule.domain.ScheduleGenerator
import com.github.googelfist.workschedule.domain.models.PreferencesModel
import java.time.LocalDate

class GetActualDateFirstWorkUseCase(
    private val scheduleGenerator: ScheduleGenerator
) {
    operator fun invoke(date: LocalDate, preference: PreferencesModel): LocalDate {
        val actualFirstWorkDate: LocalDate = if (preference.actualFirstWorkDate.isEmpty()) {
            val firstWorkDate = LocalDate.parse(preference.firstWorkDate)
            scheduleGenerator.getActualFirstDate(date, firstWorkDate)
        } else {
            val actualDate = LocalDate.parse(preference.actualFirstWorkDate)
            scheduleGenerator.getActualFirstDate(date, actualDate)
        }
        return actualFirstWorkDate
    }
}
