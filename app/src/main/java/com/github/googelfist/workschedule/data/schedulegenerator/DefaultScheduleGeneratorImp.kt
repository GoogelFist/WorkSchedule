package com.github.googelfist.workschedule.data.schedulegenerator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.googelfist.workschedule.data.schedulegenerator.daysgenerator.DaysGenerator
import com.github.googelfist.workschedule.data.schedulegenerator.formatter.DateFormatter
import com.github.googelfist.workschedule.data.schedulegenerator.models.Day
import com.github.googelfist.workschedule.domain.ScheduleGenerator
import java.time.LocalDate
import javax.inject.Inject

class DefaultScheduleGeneratorImp @Inject constructor(
    private val daysGenerator: DaysGenerator,
    private val formatter: DateFormatter
) : ScheduleGenerator {

    private val dayListLD = MutableLiveData<List<Day>>()
    private var dayList = listOf<Day>()
    private var date = LocalDate.now()
    private var formatDateLD = MutableLiveData<String>()

    override fun generateCurrentSchedule(): LiveData<List<Day>> {
        val currentDate = LocalDate.now()
        date = currentDate
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
