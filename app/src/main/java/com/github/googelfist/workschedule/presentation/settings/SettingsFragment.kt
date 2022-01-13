package com.github.googelfist.workschedule.presentation.settings

import android.app.DatePickerDialog
import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.github.googelfist.workschedule.R
import java.util.Calendar

class SettingsFragment : PreferenceFragmentCompat() {

    private lateinit var settings: SharedPreferences

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preference, rootKey)

        settings = preferenceManager.sharedPreferences

        val datePref = preferenceManager.findPreference<Preference>(getDatePickerKey())
            ?: throw NoSuchElementException("Preference not found")
        datePref.summary = settings.getString(getDatePickerKey(), PREFERENCE_DEFAULT_VALUE)
        datePref.onPreferenceClickListener = datePicker
    }

    private fun getDatePickerKey(): String {
        return preferenceManager.context.resources.getString(R.string.p_date_picker_key)
    }

    private var datePicker = Preference.OnPreferenceClickListener { preference ->
        val calendar = Calendar.getInstance()
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val year = calendar.get(Calendar.YEAR)

        val datePickerDialog = DatePickerDialog(
            requireActivity(),
            R.style.dateTimePicker,
            { _, y, m, d ->
                val datePicked = "$d.${m + ONE_VALUE}.$y"
                settings.edit().putString(getDatePickerKey(), datePicked).apply()
                preference.summary = datePicked
            }, year, month, day
        )
        datePickerDialog.show()
        false
    }

    companion object {
        private const val PREFERENCE_DEFAULT_VALUE = "Choose start work date"
        private const val ONE_VALUE = 1
    }
}
