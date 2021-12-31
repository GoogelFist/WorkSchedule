package com.github.googelfist.workshedule.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.googelfist.workshedule.domain.Day
import com.github.googelfist.workshedule.domain.Repository
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class RepositoryImpl : Repository {
    private val dayListLD = MutableLiveData<List<Day>>()
    private val dateOfChosenMonthLD = MutableLiveData<String>()

    private val daysGenerator = DaysGeneratorImpl()

    var date: LocalDate = LocalDate.now()

    // TODO: 31-Dec-21 get date from settings, rewrite logic
    private val firstWorkDate: LocalDate? = null


    override fun generateCurrentMonth(): LiveData<List<Day>> {
        date = LocalDate.now()
        val dayList = when (firstWorkDate) {
            null -> daysGenerator.generateDays(date)
            else -> {
                val actualDateFirstWork = getActualDateFirstWork(date, firstWorkDate)
                daysGenerator.generateDays(date, actualDateFirstWork)
            }
        }

        dayListLD.value = dayList
        return dayListLD
    }

    override fun generateNextMonth(): LiveData<List<Day>> {
        date = date.plusMonths(ONE_VALUE)
        val nextMonthDayList = when (firstWorkDate) {
            null -> daysGenerator.generateDays(date)
            else -> {
                val actualDateFirstWork = getActualDateFirstWork(date, firstWorkDate)
                daysGenerator.generateDays(date, actualDateFirstWork)
            }
        }
        dayListLD.value = nextMonthDayList
        return dayListLD
    }

    override fun generatePreviousMonth(): LiveData<List<Day>> {
        date = date.minusMonths(ONE_VALUE)
        val previousMonthDayList = when (firstWorkDate) {
            null -> daysGenerator.generateDays(date)
            else -> {
                val actualDateFirstWork = getActualDateFirstWork(date, firstWorkDate)
                daysGenerator.generateDays(date, actualDateFirstWork)
            }
        }
        dayListLD.value = previousMonthDayList
        return dayListLD
    }

    override fun getDateOfChosenMonth(): LiveData<String> {
        val currentDate = DateTimeFormatter.ofPattern(FORMAT_PATTERN).format(date)
        dateOfChosenMonthLD.value = currentDate
        return dateOfChosenMonthLD
    }

    private fun getActualDateFirstWork(date: LocalDate, startWorkDate: LocalDate): LocalDate {
        var curDate = startWorkDate
        when {
            curDate == date -> return date
            curDate < date -> while (curDate < date) {
                curDate = curDate.plusDays(STEP)
            }
            curDate > date -> while (curDate > date) {
                curDate = curDate.minusDays(STEP)
            }
        }
        return curDate
    }

    companion object {
        private const val ONE_VALUE = 1L
        private const val FORMAT_PATTERN = "MMM yyyy"
        private const val STEP = 4L
    }
}