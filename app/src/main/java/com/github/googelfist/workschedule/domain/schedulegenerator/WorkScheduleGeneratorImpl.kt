package com.github.googelfist.workschedule.domain.schedulegenerator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.googelfist.workschedule.domain.ScheduleGenerator
import com.github.googelfist.workschedule.domain.schedulegenerator.datecontainer.DateContainer
import com.github.googelfist.workschedule.domain.schedulegenerator.daysgenerator.DaysGenerator
import com.github.googelfist.workschedule.domain.schedulegenerator.formatter.DateFormatter
import com.github.googelfist.workschedule.domain.schedulegenerator.models.Day
import javax.inject.Inject

class WorkScheduleGeneratorImpl @Inject constructor(
    private val daysGenerator: DaysGenerator,
    private val formatter: DateFormatter,
    private val dateContainer: DateContainer
) : ScheduleGenerator {

    private val dayListLD = MutableLiveData<List<Day>>()
    private var dayList = listOf<Day>()
    private var date = dateContainer.getDateNow()
    private var formatDateLD = MutableLiveData<String>()

    override fun generateCurrentSchedule(): LiveData<List<Day>> {
        date = dateContainer.getDateNow()
        dayList = daysGenerator.generateMonthDays(date)
        dayListLD.value = dayList
        formatDateLD.value = formatter.format(date)
        return dayListLD
    }

    override fun generateNextSchedule(): LiveData<List<Day>> {
        date = date.plusMonths(ONE_VALUE)
        dayList = daysGenerator.generateMonthDays(date)
        dayListLD.value = dayList
        formatDateLD.value = formatter.format(date)
        return dayListLD
    }

    override fun generatePreviousSchedule(): LiveData<List<Day>> {
        date = date.minusMonths(ONE_VALUE)
        dayList = daysGenerator.generateMonthDays(date)
        dayListLD.value = dayList
        formatDateLD.value = formatter.format(date)
        return dayListLD
    }

    override fun getActiveFormatDate(): LiveData<String> {
        return formatDateLD
    }

    companion object {
        private const val ONE_VALUE = 1L
    }
}
