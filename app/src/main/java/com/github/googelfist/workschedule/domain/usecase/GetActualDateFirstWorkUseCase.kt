package com.github.googelfist.workschedule.domain.usecase

import java.time.LocalDate

class GetActualDateFirstWorkUseCase {

    operator fun invoke(date: LocalDate, startWorkDate: LocalDate, step: Int): LocalDate {
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
