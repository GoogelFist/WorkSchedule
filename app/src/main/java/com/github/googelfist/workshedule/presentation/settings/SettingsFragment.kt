package com.github.googelfist.workshedule.presentation.settings

import android.app.DatePickerDialog
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.github.googelfist.workshedule.R
import java.util.*


class SettingsFragment : PreferenceFragmentCompat() {
    private lateinit var settings: SharedPreferences

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        settings = PreferenceManager.getDefaultSharedPreferences(requireActivity())
        setPreferencesFromResource(R.xml.preference, rootKey)

        val datePref = preferenceManager.findPreference<Preference>(DATE_PICKER_KEY)
            ?: throw  RuntimeException("Preference not found")
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

    override fun onResume() {
        super.onResume()
        val dropDown = settings.getString(DROP_DOWN_KEY, PREFERENCE_DEFAULT_VALUE)
        val date = settings.getString(DATE_PICKER_KEY, PREFERENCE_DEFAULT_VALUE)
        Log.d("LOG", "${dropDown}")
        Log.d("LOG", "${date}")
    }

    override fun onDestroy() {
        super.onDestroy()
        val dropDown = settings.getString(DROP_DOWN_KEY, PREFERENCE_DEFAULT_VALUE)
        val date = settings.getString(DATE_PICKER_KEY, PREFERENCE_DEFAULT_VALUE)
        Log.d("LOG", "${dropDown}")
        Log.d("LOG", "${date}")
    }

    companion object {
        private const val DATE_PICKER_KEY = "datePicker"
        private const val DROP_DOWN_KEY = "dropDown"
        private const val PREFERENCE_DEFAULT_VALUE = ""
        private const val ONE_VALUE = 1
    }
}