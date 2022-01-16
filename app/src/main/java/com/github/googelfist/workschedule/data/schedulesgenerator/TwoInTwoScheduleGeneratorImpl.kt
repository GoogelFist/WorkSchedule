package com.github.googelfist.workschedule.data.schedulesgenerator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.googelfist.workschedule.data.schedulesgenerator.daysgenerator.DaysGenerator
import com.github.googelfist.workschedule.domain.ScheduleGenerator
import com.github.googelfist.workschedule.domain.models.days.Day
import java.time.LocalDate

class TwoInTwoScheduleGeneratorImpl(
    private val daysGenerator: DaysGenerator
) : ScheduleGenerator {
    private val dayListLD = MutableLiveData<List<Day>>()

    override fun generateSchedule(
        activeDate: LocalDate,
        firstWorkDate: LocalDate
    ): LiveData<List<Day>> {
        val dayList = daysGenerator.generateDays(activeDate, firstWorkDate)
        dayListLD.value = dayList
        return dayListLD
    }

    override fun getActualFirstDate(activeDate: LocalDate, firstWorkDate: LocalDate): LocalDate {
        var curDate = firstWorkDate
        when {
            curDate == activeDate -> return activeDate
            curDate < activeDate -> while (curDate < activeDate) {
                curDate = curDate.plusDays(STEP)
            }
            curDate > activeDate -> while (curDate > activeDate) {
                curDate = curDate.minusDays(STEP)
            }
        }
        return curDate
    }

    companion object {
        private const val STEP = 4L
    }
}
