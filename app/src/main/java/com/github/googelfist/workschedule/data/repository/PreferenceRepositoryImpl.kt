package com.github.googelfist.workschedule.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.github.googelfist.workschedule.R
import com.github.googelfist.workschedule.domain.PreferenceRepository
import com.github.googelfist.workschedule.domain.models.PreferencesModel

class PreferenceRepositoryImpl(private val context: Context) : PreferenceRepository {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    override fun loadPreference(): PreferencesModel {
        val firstWorkDate = getDate(sharedPreferences)
        val scheduleType = getScheduleType(sharedPreferences)
        val actualFirstWorkDate = getActualFirstWorkDate(sharedPreferences)
        return PreferencesModel(
            scheduleType = scheduleType,
            firstWorkDate = firstWorkDate,
            actualFirstWorkDate = actualFirstWorkDate
        )
    }

    override fun savePreference(preference: PreferencesModel) {
        sharedPreferences.edit()
            .putString(getDropDownKey(), preference.scheduleType)
            .putString(getDatePickerKey(), preference.firstWorkDate)
            .putString(ACTUAL_FIRST_WORK_DATE_KEY, preference.actualFirstWorkDate)
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

    private fun getActualFirstWorkDate(sharedPreferences: SharedPreferences): String {
        return sharedPreferences.getString(
            ACTUAL_FIRST_WORK_DATE_KEY,
            ACTUAL_FIRST_WORK_DATE_DEFAULT_VALUE
        ) ?: ACTUAL_FIRST_WORK_DATE_DEFAULT_VALUE
    }

    private fun getDropDownKey() = context.resources.getString(R.string.dd_schedule_type_key)

    private fun getDatePickerKey() = context.resources.getString(R.string.p_date_picker_key)

    companion object {
        private const val ACTUAL_FIRST_WORK_DATE_KEY = "actualFirstWorkDate"
        private const val ACTUAL_FIRST_WORK_DATE_DEFAULT_VALUE = ""
        private const val PREFERENCE_NAME = "com.github.googelfist.workshedule_preferences"
        private const val DATE_PICKER_DEFAULT_VALUE = "Choose start work date"
        private const val DROPDOWN_DEFAULT_VALUE = "Choose the schedule"
    }
}
