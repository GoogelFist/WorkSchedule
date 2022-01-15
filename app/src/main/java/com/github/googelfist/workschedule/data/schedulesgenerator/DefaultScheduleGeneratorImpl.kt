package com.github.googelfist.workschedule.data.schedulesgenerator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.googelfist.workschedule.data.schedulesgenerator.daysgenerator.DaysGenerator
import com.github.googelfist.workschedule.domain.ScheduleGenerator
import com.github.googelfist.workschedule.domain.models.days.Day
import java.time.LocalDate

class DefaultScheduleGeneratorImpl(
    private val daysGenerator: DaysGenerator,
) : ScheduleGenerator {
    private val dayListLD = MutableLiveData<List<Day>>()

    override fun generateSchedule(date: LocalDate, firstWorkDate: LocalDate): LiveData<List<Day>> {
        val dayList = daysGenerator.generateDays(date)
        dayListLD.value = dayList
        return dayListLD
    }

    override fun getActualFirstDate(date: LocalDate, firstWorkDate: LocalDate): LocalDate {
        TODO("Not yet implemented")
    }
}
