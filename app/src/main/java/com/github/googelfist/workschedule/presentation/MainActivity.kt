package com.github.googelfist.workschedule.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.github.googelfist.workschedule.R
import com.github.googelfist.workschedule.di.PreferenceViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var preferenceViewModel: PreferenceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preferenceViewModel = ViewModelProvider(
            this,
            PreferenceViewModelFactory(this)
        )[PreferenceViewModel::class.java]

        val scheduleType = preferenceViewModel.preferencesLD.scheduleType

        when (scheduleType) {
            TWO_IN_TWO -> launchTwoInTwoSchedule()
            DEFAULT -> launchDefaultSchedule()
            else -> launchDefaultSchedule()
        }
    }

    private fun launchTwoInTwoSchedule() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_activity_container, WorkScheduleFragment.newTwoInTwoFragment())
            .commit()
    }

    private fun launchDefaultSchedule() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_activity_container, DefaultScheduleFragment.newInstance())
            .commit()
    }

    companion object {
        private const val DEFAULT = "Default"
        private const val TWO_IN_TWO = "2 / 2"

        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}
