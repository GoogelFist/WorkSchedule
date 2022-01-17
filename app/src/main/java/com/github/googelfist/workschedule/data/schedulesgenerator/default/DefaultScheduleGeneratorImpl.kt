package com.github.googelfist.workschedule.data.schedulesgenerator.default

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.googelfist.workschedule.data.schedulesgenerator.default.generator.DefaultDaysGenerator
import com.github.googelfist.workschedule.domain.DefaultScheduleGenerator
import com.github.googelfist.workschedule.domain.models.days.Day
import java.time.LocalDate

class DefaultScheduleGeneratorImpl(
    private val daysGenerator: DefaultDaysGenerator,
) : DefaultScheduleGenerator {
    private val dayListLD = MutableLiveData<List<Day>>()

    override fun generateSchedule(activeDate: LocalDate): LiveData<List<Day>> {
        val dayList = daysGenerator.generateDays(activeDate)
        dayListLD.value = dayList
        return dayListLD
    }
}
