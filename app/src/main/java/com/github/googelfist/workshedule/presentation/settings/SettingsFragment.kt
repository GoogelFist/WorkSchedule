package com.github.googelfist.workshedule.presentation.settings

import android.app.DatePickerDialog
import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.github.googelfist.workshedule.R
import java.util.Calendar

class SettingsFragment : PreferenceFragmentCompat() {

    private lateinit var settings: SharedPreferences

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preference, rootKey)

        settings = preferenceManager.sharedPreferences

        val datePref = preferenceManager.findPreference<Preference>(DATE_PICKER_KEY)
            ?: throw NoSuchElementException("Preference not found")
        datePref.summary = settings.getString(DATE_PICKER_KEY, PREFERENCE_DEFAULT_VALUE)
        datePref.onPreferenceClickListener = datePicker
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
                settings.edit().putString(DATE_PICKER_KEY, datePicked).apply()
                preference.summary = datePicked
            }, year, month, day
        )
        datePickerDialog.show()
        false
    }

    companion object {
        private const val DATE_PICKER_KEY = "datePicker"
        private const val PREFERENCE_DEFAULT_VALUE = "0001-1-1"
        private const val ONE_VALUE = 1
    }
}
