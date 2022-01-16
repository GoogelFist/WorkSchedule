package com.github.googelfist.workschedule.data.schedulesgenerator.generator

import com.github.googelfist.workschedule.domain.models.days.Day
import java.time.LocalDate

interface WorkDaysGenerator {
    fun generateDays(activeDate: LocalDate, firstWorkDate: LocalDate): List<Day>
}
