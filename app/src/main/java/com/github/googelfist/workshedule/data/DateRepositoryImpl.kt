package com.github.googelfist.workshedule.data

import android.util.Log
import com.github.googelfist.workshedule.domain.DateRepository
import com.github.googelfist.workshedule.presentation.settings.Preference
import java.time.LocalDate

class DateRepositoryImpl : DateRepository {

    lateinit var preferenceRepo: Preference

    // TODO: 02-Jan-22 wrong architecture
    override fun savePreference(preference: Preference) {
        preferenceRepo = preference
        Log.d("LOG", "$preference")
    }

    override fun loadPreference(): Preference {
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