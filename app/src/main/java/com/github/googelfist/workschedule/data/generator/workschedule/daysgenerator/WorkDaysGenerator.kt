package com.github.googelfist.workschedule.data.generator.workschedule.daysgenerator

import com.github.googelfist.workschedule.domain.models.days.Day
import java.time.LocalDate

interface WorkDaysGenerator {

    fun generateWorkDays(activeDate: LocalDate, firstWorkDate: LocalDate): List<Day>
}
