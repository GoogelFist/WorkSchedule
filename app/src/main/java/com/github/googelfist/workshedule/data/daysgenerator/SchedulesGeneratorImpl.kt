package com.github.googelfist.workshedule.data.daysgenerator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.googelfist.workshedule.domain.SchedulesGenerator
import com.github.googelfist.workshedule.domain.models.Day
import java.time.LocalDate

class SchedulesGeneratorImpl(private val daysGenerator: DaysGenerator) : SchedulesGenerator {
    private val dayListLD = MutableLiveData<List<Day>>()

    override fun generateScheduleWorkDays(
        date: LocalDate,
        firstWorkDate: LocalDate,
        step: Int
    ): LiveData<List<Day>> {
        val dayList = daysGenerator.generateDays(date)
        dayList.setWeekendDays(firstWorkDate, step)
        dayList.setWorkDays(firstWorkDate, step)
        dayListLD.value = dayList
        return dayListLD
    }

    override fun generateScheduleDays(date: LocalDate): LiveData<List<Day>> {
        val dayList = daysGenerator.generateDays(date)
        dayListLD.value = dayList
        return dayListLD
    }

    private fun List<Day>.setWorkDays(date: LocalDate, step: Int) {
        val workDays = getWorkDays(date, step)

        this.map { it.isWork = workDays.contains(LocalDate.of(it.year, it.month, it.value)) }
    }

    private fun List<Day>.setWeekendDays(date: LocalDate, step: Int) {
        val weekendDays = getWeekendDays(date, step)

        this.map { it.isWeekend = weekendDays.contains(LocalDate.of(it.year, it.month, it.value)) }
    }

    private fun getWorkDays(date: LocalDate, step: Int): Set<LocalDate> {
        var lowDate = date.minusDays(RANGE_HALF_LENGTH)
        val dateSet = mutableSetOf<LocalDate>()

        for (i in 0..RANGE_LENGTH step step) {
            dateSet.add(lowDate)
            dateSet.add(lowDate.plusDays(ONE_VALUE))
            lowDate = lowDate.plusDays(step.toLong())
        }
        return dateSet
    }

    private fun getWeekendDays(date: LocalDate, step: Int): Set<LocalDate> {
        var lowDate = date.minusDays(RANGE_HALF_LENGTH - TWO_VALUE)
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
        private const val TWO_VALUE = 2L
        private const val RANGE_LENGTH = 80
        private const val RANGE_HALF_LENGTH = RANGE_LENGTH / 2L
    }
}