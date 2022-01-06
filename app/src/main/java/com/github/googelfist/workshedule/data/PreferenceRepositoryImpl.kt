package com.github.googelfist.workshedule.data

import java.time.LocalDate

class DateRepositoryImpl {

    fun loadPreference(): String {
        TODO()
    }

    // TODO: 02-Jan-22 use it in future in useCase, add step parameter
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
        private const val STEP = 4L
    }
}