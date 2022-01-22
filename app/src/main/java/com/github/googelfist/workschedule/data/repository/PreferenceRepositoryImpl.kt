package com.github.googelfist.workschedule.data.repository

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.github.googelfist.workschedule.R
import com.github.googelfist.workschedule.data.repository.model.Preferences
import com.github.googelfist.workschedule.domain.PreferenceRepository

class PreferenceRepositoryImpl(private val application: Application) : PreferenceRepository {

    private val sharedPreferences: SharedPreferences =
        application.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    override fun loadPreference(): Preferences {
        val firstWorkDate = getDate(sharedPreferences)
        val scheduleType = getScheduleType(sharedPreferences)
        return Preferences(
            scheduleType = scheduleType,
            firstWorkDate = firstWorkDate
        )
    }

    override fun savePreference(preference: Preferences) {
        sharedPreferences.edit()
            .putString(getDropDownKey(), preference.scheduleType)
            .putString(getDatePickerKey(), preference.firstWorkDate)
            .apply()
    }

    private fun getDate(sharedPreferences: SharedPreferences): String {
        return sharedPreferences.getString(
            getDatePickerKey(),
            DATE_PICKER_DEFAULT_VALUE
        ) ?: DATE_PICKER_DEFAULT_VALUE
    }

    private fun getScheduleType(sharedPreferences: SharedPreferences): String {
        return sharedPreferences.getString(
            getDropDownKey(),
            DROPDOWN_DEFAULT_VALUE
        ) ?: DROPDOWN_DEFAULT_VALUE
    }

    private fun getDropDownKey() = application.resources.getString(R.string.dd_schedule_type_key)

    private fun getDatePickerKey() = application.resources.getString(R.string.p_date_picker_key)

    companion object {
        private const val PREFERENCE_NAME = "com.github.googelfist.workshedule_preferences"
        private const val DATE_PICKER_DEFAULT_VALUE = "Choose start work date"
        private const val DROPDOWN_DEFAULT_VALUE = "Choose the schedule"
    }
}
