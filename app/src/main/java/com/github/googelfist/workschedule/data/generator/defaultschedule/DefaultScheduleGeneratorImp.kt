package com.github.googelfist.workschedule.data.generator.defaultschedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.googelfist.workschedule.data.generator.defaultschedule.daysgenerator.DefaultDaysGenerator
import com.github.googelfist.workschedule.data.generator.formatter.DateFormatter
import com.github.googelfist.workschedule.domain.DefaultScheduleGenerator
import com.github.googelfist.workschedule.domain.models.days.Day
import java.time.LocalDate

class DefaultScheduleGeneratorImp(
    private val daysGenerator: DefaultDaysGenerator,
    private val formatter: DateFormatter
) :
    DefaultScheduleGenerator {

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
