package com.github.googelfist.workshedule.data.schedulesgenerator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.googelfist.workshedule.data.schedulesgenerator.daysgenerator.DaysGenerator
import com.github.googelfist.workshedule.data.schedulesgenerator.daysmapper.DaysMapper
import com.github.googelfist.workshedule.domain.SchedulesGenerator
import com.github.googelfist.workshedule.domain.models.days.Day
import java.time.LocalDate

class SchedulesGeneratorImpl(
    private val daysGenerator: DaysGenerator,
    private val dayMapper: DaysMapper
) : SchedulesGenerator {
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
            if (isWorkDay(workDates, it)) {
                dayMapper.dayToWorkDay(it)
            } else {
                dayMapper.dayToWeekendDay(it)
            }
        }
    }

    private fun getWorkDates(date: LocalDate, step: Int): Set<LocalDate> {
        var lowDate = date.minusDays(RANGE_HALF_LENGTH)
        val dateSet = mutableSetOf<LocalDate>()

        (ZERO..RANGE_LENGTH step step).forEach { _ ->
            dateSet.add(lowDate)
            dateSet.add(lowDate.plusDays(ONE_VALUE))
            lowDate = lowDate.plusDays(step.toLong())
        }
        return dateSet
    }

    private fun isWorkDay(workDates: Set<LocalDate>, day: Day): Boolean {
        return workDates.contains(LocalDate.of(day.year, day.month, day.value))
    }

    companion object {
        private const val ONE_VALUE = 1L
        private const val RANGE_LENGTH = 80
        private const val RANGE_HALF_LENGTH = RANGE_LENGTH / 2L
        private const val ZERO = 0
    }
}
