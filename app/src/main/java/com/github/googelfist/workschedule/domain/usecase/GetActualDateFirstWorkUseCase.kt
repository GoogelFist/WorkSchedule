package com.github.googelfist.workschedule.domain.usecase

import com.github.googelfist.workschedule.domain.models.PreferencesModel
import java.time.LocalDate

class GetActualDateFirstWorkUseCase {
    operator fun invoke(date: LocalDate, preference: PreferencesModel): LocalDate {
        val step = when (preference.scheduleType) {
            TWO_IN_TWO -> STEP_TWO_IN_TWO
            else -> STEP_DEFAULT
        }
        val actualFirstWorkDate: LocalDate = if (preference.actualFirstWorkDate.isEmpty()) {
            val firstWorkDate = LocalDate.parse(preference.firstWorkDate)
            getActualFirstDate(date, firstWorkDate, step)
        } else {
            val actualDate = LocalDate.parse(preference.actualFirstWorkDate)
            getActualFirstDate(date, actualDate, step)
        }
        return actualFirstWorkDate
    }

    private fun getActualFirstDate(
        activeDate: LocalDate,
        firstWorkDate: LocalDate,
        step: Long
    ): LocalDate {
        var curDate = firstWorkDate
        when {
            curDate == activeDate -> return activeDate
            curDate < activeDate -> while (curDate < activeDate) {
                curDate = curDate.plusDays(step)
            }
            curDate > activeDate -> while (curDate > activeDate) {
                curDate = curDate.minusDays(step)
            }
        }
        return curDate
    }

    companion object {
        private const val STEP_TWO_IN_TWO = 4L
        private const val STEP_DEFAULT = 4L
        private const val TWO_IN_TWO = "2 / 2"
    }
}
