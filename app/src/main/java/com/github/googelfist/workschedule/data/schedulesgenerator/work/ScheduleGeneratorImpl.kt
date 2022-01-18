package com.github.googelfist.workschedule.data.schedulesgenerator.work

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.googelfist.workschedule.data.schedulesgenerator.work.generator.DaysGenerator
import com.github.googelfist.workschedule.domain.ScheduleGenerator
import com.github.googelfist.workschedule.domain.models.days.Day
import java.time.LocalDate

class ScheduleGeneratorImpl(
    private val daysGenerator: DaysGenerator
) : ScheduleGenerator {
    private val dayListLD = MutableLiveData<List<Day>>()

    override fun generateWorkSchedule(
        activeDate: LocalDate,
        firstWorkDate: LocalDate
    ): LiveData<List<Day>> {
        val dayList = daysGenerator.generateWorkDays(activeDate, firstWorkDate)
        dayListLD.value = dayList
        return dayListLD
    }

    override fun generateDefaultSchedule(activeDate: LocalDate): LiveData<List<Day>> {
        val dayList = daysGenerator.generateDefaultDays(activeDate)
        dayListLD.value = dayList
        return dayListLD
    }
}
