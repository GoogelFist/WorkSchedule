package com.github.googelfist.workschedule.domain

import androidx.lifecycle.LiveData
import com.github.googelfist.workschedule.domain.models.days.Day
import java.time.LocalDate

interface DefaultScheduleGenerator {

    fun generateSchedule(activeDate: LocalDate): LiveData<List<Day>>
}
