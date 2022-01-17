package com.github.googelfist.workschedule.data.schedulesgenerator.default.generator

import com.github.googelfist.workschedule.domain.models.days.Day
import java.time.LocalDate

interface DefaultDaysGenerator {

    fun generateDays(activeDate: LocalDate): List<Day>
}
