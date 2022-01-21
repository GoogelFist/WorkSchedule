package com.github.googelfist.workschedule.data.generator.workschedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.googelfist.workschedule.data.generator.formatter.DateFormatter
import com.github.googelfist.workschedule.data.generator.scheduletype.ScheduleTyper
import com.github.googelfist.workschedule.data.generator.workschedule.daysgenerator.WorkDaysGenerator
import com.github.googelfist.workschedule.domain.WorkScheduleGenerator
import com.github.googelfist.workschedule.domain.models.days.Day
import java.time.LocalDate

class WorkScheduleGeneratorImpl(
    private val daysGenerator: WorkDaysGenerator,
    private val formatter: DateFormatter,
    private val scheduleTyper: ScheduleTyper
) : WorkScheduleGenerator {
    private val dayListLD = MutableLiveData<List<Day>>()
    private var dayList = listOf<Day>()
    private var date = LocalDate.now()
    private var formatDateLD = MutableLiveData<String>()

    override fun generateWorkCurrentSchedule(
        firstWorkDate: LocalDate
    ): LiveData<List<Day>> {
        val currentDate = LocalDate.now()
        date = currentDate
        val actualFirstDate = scheduleTyper.getActualFirstDate(date, firstWorkDate)
        dayList = daysGenerator.generateWorkDays(date, actualFirstDate)
        dayListLD.value = dayList
        formatDateLD.value = formatter.format(date)
        return dayListLD
    }

    override fun generateWorkNexSchedule(
        firstWorkDate: LocalDate
    ): LiveData<List<Day>> {
        date = date.plusMonths(ONE_VALUE)
        val actualFirstDate = scheduleTyper.getActualFirstDate(date, firstWorkDate)
        dayList = daysGenerator.generateWorkDays(date, actualFirstDate)
        dayListLD.value = dayList
        formatDateLD.value = formatter.format(date)
        return dayListLD
    }

    override fun generateWorkPreviousSchedule(
        firstWorkDate: LocalDate
    ): LiveData<List<Day>> {
        date = date.minusMonths(ONE_VALUE)
        val actualFirstDate = scheduleTyper.getActualFirstDate(date, firstWorkDate)
        dayList = daysGenerator.generateWorkDays(date, actualFirstDate)
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
