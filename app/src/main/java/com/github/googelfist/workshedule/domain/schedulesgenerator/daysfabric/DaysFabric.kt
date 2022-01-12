package com.github.googelfist.workshedule.domain.schedulesgenerator.daysfabric

import com.github.googelfist.workshedule.domain.models.days.Day
import java.time.LocalDate

interface DaysFabric {
    fun getDays(firstDate: LocalDate, date: LocalDate): Day
}
