package com.github.googelfist.workshedule.domain.schedulesgenerator.daysmapper

import com.github.googelfist.workshedule.domain.models.days.ActiveDay
import com.github.googelfist.workshedule.domain.models.days.Day
import com.github.googelfist.workshedule.domain.models.days.InActiveDay
import com.github.googelfist.workshedule.domain.models.days.Today

class DaysMapperImpl : DaysMapper {
    override fun dayToWorkDay(day: Day): Day {
        return when (day) {
            is ActiveDay -> ActiveDay(
                value = day.value,
                month = day.month,
                year = day.year,
                isWeekend = false,
                isWork = true
            )
            is Today -> Today(
                value = day.value,
                month = day.month,
                year = day.year,
                isWeekend = false,
                isWork = true
            )
            else -> InActiveDay(
                value = day.value,
                month = day.month,
                year = day.year,
                isWeekend = false,
                isWork = true
            )
        }
    }

    override fun dayToWeekendDay(day: Day): Day {
        return when (day) {
            is ActiveDay -> ActiveDay(
                value = day.value,
                month = day.month,
                year = day.year,
                isWeekend = true,
                isWork = false
            )
            is Today -> Today(
                value = day.value,
                month = day.month,
                year = day.year,
                isWeekend = true,
                isWork = false
            )
            else -> InActiveDay(
                value = day.value,
                month = day.month,
                year = day.year,
                isWeekend = true,
                isWork = false
            )
        }
    }
}
