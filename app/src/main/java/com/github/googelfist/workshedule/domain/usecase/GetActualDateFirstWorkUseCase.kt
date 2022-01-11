package com.github.googelfist.workshedule.domain.usecase

import java.time.LocalDate

class GetActualDateFirstWorkUseCase {

    fun getActualDateFirstWork(date: LocalDate, startWorkDate: LocalDate, step: Int): LocalDate {
        var curDate = startWorkDate
        when {
            curDate == date -> return date
            curDate < date -> while (curDate < date) {
                curDate = curDate.plusDays(step.toLong())
            }
            curDate > date -> while (curDate > date) {
                curDate = curDate.minusDays(step.toLong())
            }
        }
        return curDate
    }
}
