package com.github.googelfist.workshedule.data

import android.util.Log
import com.github.googelfist.workshedule.domain.DateRepository
import com.github.googelfist.workshedule.domain.models.DatePreference
import com.github.googelfist.workshedule.domain.models.SchedulePreference
import java.time.LocalDate

class DateRepositoryImpl : DateRepository {

    // TODO: 02-Jan-22 wrong architecture
    override fun saveDatePreference(datePreferenceModel: DatePreference) {
        Log.d("LOG", "datePreference: $datePreferenceModel")
    }

    override fun saveSchedulePreference(schedulePreferenceModel: SchedulePreference) {
        Log.d("LOG", "schedulePreference: $schedulePreferenceModel")
    }

    override fun loadPreference(): String{
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