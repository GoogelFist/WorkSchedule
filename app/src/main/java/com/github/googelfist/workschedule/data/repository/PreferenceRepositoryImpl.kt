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
        return PreferencesModel(scheduleType = scheduleType, firstWorkDate = firstWorkDate)
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

    private fun getDropDownKey() = context.resources.getString(R.string.dd_schedule_type_key)

    private fun getDatePickerKey() = context.resources.getString(R.string.p_date_picker_key)

    companion object {
        private const val PREFERENCE_NAME = "com.github.googelfist.workshedule_preferences"
        private const val DATE_PICKER_DEFAULT_VALUE = "Choose start work date"
        private const val DROPDOWN_DEFAULT_VALUE = "Choose the schedule"
    }
}
