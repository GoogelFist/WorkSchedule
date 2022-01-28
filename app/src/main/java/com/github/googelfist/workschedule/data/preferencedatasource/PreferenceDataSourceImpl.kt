package com.github.googelfist.workschedule.data.preferencedatasource

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.github.googelfist.workschedule.R
import com.github.googelfist.workschedule.data.preferencedatasource.model.PreferencesData
import javax.inject.Inject

class PreferenceDataSourceImpl @Inject constructor(private val application: Application) :
    PreferenceDataSource {

    private val sharedPreferences: SharedPreferences =
        application.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    override fun loadPreference(): PreferencesData {
        val firstWorkDate = getDate(sharedPreferences)
        val scheduleType = getScheduleType(sharedPreferences)
        return PreferencesData(
            scheduleType = scheduleType,
            firstWorkDate = firstWorkDate
        )
    }

    override fun savePreference(preference: PreferencesData) {
        sharedPreferences.edit()
            .putString(getDropDownKey(), preference.scheduleType)
            .putString(getDatePickerKey(), preference.firstWorkDate)
            .apply()
    }

    override fun generateDefaultPreference(): PreferencesData {
        return PreferencesData(
            scheduleType = DROPDOWN_DEFAULT_VALUE,
            firstWorkDate = DATE_PICKER_DEFAULT_VALUE
        )
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
