package com.github.googelfist.workshedule.data

import android.content.Context
import android.content.SharedPreferences
import com.github.googelfist.workshedule.R
import com.github.googelfist.workshedule.domain.PreferenceRepository
import com.github.googelfist.workshedule.domain.models.PreferencesModel

class PreferenceRepositoryImpl(private val context: Context) : PreferenceRepository {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    override fun loadPreference(): PreferencesModel {
        val firstWorkDate = sharedPreferences.getString(
            getDatePickerKey(),
            DATE_PICKER_DEFAULT_VALUE
        ) ?: DATE_PICKER_DEFAULT_VALUE

        val scheduleType = sharedPreferences.getString(
            getDropDownKey(),
            DROPDOWN_DEFAULT_VALUE
        ) ?: DROPDOWN_DEFAULT_VALUE

        return PreferencesModel(scheduleType = scheduleType, firstWorkDate = firstWorkDate)
    }

    private fun getDropDownKey() = context.resources.getString(R.string.dd_schedule_type_key)

    private fun getDatePickerKey() = context.resources.getString(R.string.p_date_picker_key)

    companion object {
        private const val PREFERENCE_NAME = "com.github.googelfist.workshedule_preferences"
        private const val DATE_PICKER_DEFAULT_VALUE = "Choose start work date"
        private const val DROPDOWN_DEFAULT_VALUE = "Choose the schedule"
    }
}
