package com.github.googelfist.workshedule.domain.schedulesgenerator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.googelfist.workshedule.domain.models.Day
import com.github.googelfist.workshedule.domain.schedulesgenerator.daysgenerator.DaysGenerator
import java.time.LocalDate

class SchedulesGeneratorImpl(private val daysGenerator: DaysGenerator) : SchedulesGenerator {
    private val dayListLD = MutableLiveData<List<Day>>()

    override fun generateScheduleWorkDays(
        date: LocalDate,
        firstWorkDate: LocalDate,
        step: Int
    ): LiveData<List<Day>> {
        val dayList = daysGenerator.generateDays(date)
        dayListLD.value = dayList.createSchedule(firstWorkDate, step)
        return dayListLD
    }

    override fun generateScheduleDays(date: LocalDate): LiveData<List<Day>> {
        val dayList = daysGenerator.generateDays(date)
        dayListLD.value = dayList
        return dayListLD
    }

    private fun List<Day>.createSchedule(date: LocalDate, step: Int): List<Day> {
        val workDates = getWorkDates(date, step)

        return this.map {
            if (workDates.contains(LocalDate.of(it.year, it.month, it.value))) {
                it.copy(isWork = true)
            } else {
                it.copy(isWeekend = true)
            }
        }
    }

    private fun getWorkDates(date: LocalDate, step: Int): Set<LocalDate> {
        var lowDate = date.minusDays(RANGE_HALF_LENGTH)
        val dateSet = mutableSetOf<LocalDate>()

        for (i in 0..RANGE_LENGTH step step) {
            dateSet.add(lowDate)
            dateSet.add(lowDate.plusDays(ONE_VALUE))
            lowDate = lowDate.plusDays(step.toLong())
        }
        return dateSet
    }

    companion object {
        private const val ONE_VALUE = 1L
        private const val RANGE_LENGTH = 80
        private const val RANGE_HALF_LENGTH = RANGE_LENGTH / 2L
    }
}
