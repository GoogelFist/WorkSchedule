package com.github.googelfist.workschedule.data.schedulesgenerator.work

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.googelfist.workschedule.data.schedulesgenerator.work.generator.WorkDaysGenerator
import com.github.googelfist.workschedule.domain.ScheduleGenerator
import com.github.googelfist.workschedule.domain.models.days.Day
import java.time.LocalDate

class TwoInTwoScheduleGeneratorImpl(
    private val workDaysGenerator: WorkDaysGenerator
) : ScheduleGenerator {
    private val dayListLD = MutableLiveData<List<Day>>()

    override fun generateSchedule(
        activeDate: LocalDate,
        firstWorkDate: LocalDate
    ): LiveData<List<Day>> {
        val dayList = workDaysGenerator.generateDays(activeDate, firstWorkDate)
        dayListLD.value = dayList
        return dayListLD
    }
}
