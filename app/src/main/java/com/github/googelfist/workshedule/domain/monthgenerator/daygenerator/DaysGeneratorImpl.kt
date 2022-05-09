package com.github.googelfist.workshedule.domain.monthgenerator.daygenerator

import com.github.googelfist.workshedule.domain.Repository
import com.github.googelfist.workshedule.domain.models.day.Day
import com.github.googelfist.workshedule.domain.monthgenerator.DayType
import com.github.googelfist.workshedule.domain.monthgenerator.fabric.DaysFabric
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import javax.inject.Inject
import kotlin.math.abs

class DaysGeneratorImpl @Inject constructor(
    private val daysFabric: DaysFabric,
    private val repository: Repository,
    private val dispatcher: CoroutineDispatcher
) : DaysGenerator {

    override suspend fun getDays(date: LocalDate): List<Day> {
        val schedulePattern = repository.getSchedulePattern()

        // TODO: temp
        val firstDate = LocalDate.of(2022, 5, 2)

        return generateWorkDays(firstDate, schedulePattern, date)
    }

    private suspend fun generateWorkDays(
        firstScheduleDate: LocalDate,
        schedulePattern: List<DayType>,
        date: LocalDate
    ): List<Day> {
        return withContext(dispatcher) {
            val dayList = ArrayList<Day>(MAX_DAY_COUNT)

            var firstMondayDate = getDateOfFirstMonday(date)

            val patternSize = schedulePattern.size

            var typePointer =
                getTypePointerOfFirstMonday(firstScheduleDate, firstMondayDate, patternSize)

            repeat(MAX_DAY_COUNT) {

                if (typePointer >= patternSize) {
                    typePointer = 0
                }
                val dayType = schedulePattern[typePointer]

                dayList.add(daysFabric.getDay(dayType, firstMondayDate, date))

                typePointer++

                firstMondayDate = firstMondayDate.plusDays(ONE_VALUE)
            }
            dayList
        }
    }

    private fun getTypePointerOfFirstMonday(
        firstScheduleDate: LocalDate,
        firstMondayDate: LocalDate,
        schedulePatternSize: Int
    ): Int {
        val diff = ChronoUnit.DAYS.between(firstScheduleDate, firstMondayDate).toInt()
        return schedulePatternSize - (abs(diff) % schedulePatternSize)
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