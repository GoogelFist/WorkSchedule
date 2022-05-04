package com.github.googelfist.workshedule.domain.monthgenerator.daygenerator

import com.github.googelfist.workshedule.domain.Repository
import com.github.googelfist.workshedule.domain.models.ScheduleTypeState
import com.github.googelfist.workshedule.domain.models.day.Day
import com.github.googelfist.workshedule.domain.monthgenerator.fabric.DaysFabric
import com.github.googelfist.workshedule.domain.monthgenerator.schedulecreator.ScheduleCreator
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.time.LocalDate
import javax.inject.Inject

class DaysGeneratorImpl @Inject constructor(
    private val daysFabric: DaysFabric,
    private val repository: Repository,
    private val dispatcher: CoroutineDispatcher,
    private val scheduleCreator: ScheduleCreator
) : DaysGenerator {

    override suspend fun getDays(date: LocalDate): List<Day> {
        val scheduleTypeState = repository.loadScheduleType()

        return when (scheduleTypeState) {
            is ScheduleTypeState.TwoInTwo -> generateWorkDays(date, scheduleTypeState)
            is ScheduleTypeState.Default -> generateDefaultDays(date)
        }
    }

    private suspend fun generateDefaultDays(date: LocalDate): List<Day> {
        return withContext(dispatcher) {
            var firstMondayInMonth = getDateOfFirstMonday(date)

            val dayList = ArrayList<Day>(MAX_DAY_COUNT)
            repeat(MAX_DAY_COUNT) {
                dayList.add(daysFabric.getDefaultDay(firstMondayInMonth, date))

                firstMondayInMonth = firstMondayInMonth.plusDays(ONE_VALUE)
            }
            dayList
        }
    }

    private suspend fun generateWorkDays(
        date: LocalDate,
        scheduleTypeState: ScheduleTypeState
    ): List<Day> {
        return withContext(dispatcher) {
            var firstMondayInMonth = getDateOfFirstMonday(date)

            val workSchedule = scheduleCreator.createWorkSchedule(scheduleTypeState, date)

            val dayList = ArrayList<Day>(MAX_DAY_COUNT)
            repeat(MAX_DAY_COUNT) {
                dayList.add(daysFabric.getWorkDay(firstMondayInMonth, date, workSchedule))

                firstMondayInMonth = firstMondayInMonth.plusDays(ONE_VALUE)
            }
            dayList
        }
    }

    private suspend fun getDateOfFirstMonday(date: LocalDate): LocalDate {
        return withContext(dispatcher) {
            val firstDayOfMonth = LocalDate.of(date.year, date.month, ONE_VALUE.toInt())
            val dayOfWeek = firstDayOfMonth.dayOfWeek.value - ONE_VALUE

            firstDayOfMonth.minusDays(dayOfWeek)
        }
    }

    companion object {
        private const val ONE_VALUE = 1L
        private const val MAX_DAY_COUNT = 42
    }
}