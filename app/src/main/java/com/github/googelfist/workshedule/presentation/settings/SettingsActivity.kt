package com.github.googelfist.workshedule.presentation.settings

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.github.googelfist.workshedule.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    private lateinit var settings: SharedPreferences

    private lateinit var binding: ActivitySettingsBinding

    private lateinit var viewModel: SettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        settings = PreferenceManager.getDefaultSharedPreferences(this)

        viewModel = ViewModelProvider(this)[SettingsViewModel::class.java]

        supportFragmentManager
            .beginTransaction()
            .replace(binding.flSettings.id, SettingsFragment())
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        val preference = getPreference(settings)
        viewModel.savePreference(preference)
    }

    private fun getPreference(settings: SharedPreferences): Preference {
        val schedule = settings.getString(DROP_DOWN_KEY, PREFERENCE_SCHEDULE_DEFAULT_VALUE)
            ?: PREFERENCE_SCHEDULE_DEFAULT_VALUE

        val date = settings.getString(DATE_PICKER_KEY, PREFERENCE_DATE_DEFAULT_VALUE)
            ?: PREFERENCE_DATE_DEFAULT_VALUE

        return Preference(schedule, date)
    }

    companion object {
        private const val DATE_PICKER_KEY = "datePicker"
        private const val DROP_DOWN_KEY = "dropDown"
        private const val PREFERENCE_SCHEDULE_DEFAULT_VALUE = "Schedule preference default value"
        private const val PREFERENCE_DATE_DEFAULT_VALUE = "Date preference default value"
    }
}