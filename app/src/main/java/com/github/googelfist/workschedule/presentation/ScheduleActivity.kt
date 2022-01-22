package com.github.googelfist.workschedule.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.github.googelfist.workschedule.R
import com.github.googelfist.workschedule.di.PreferenceViewModelFactory
import com.github.googelfist.workschedule.presentation.fragments.DefaultScheduleFragment
import com.github.googelfist.workschedule.presentation.fragments.WorkScheduleFragment

class ScheduleActivity : AppCompatActivity() {

    private lateinit var preferenceViewModel: PreferenceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.schedule_activity_container)

        preferenceViewModel = ViewModelProvider(
            this,
            PreferenceViewModelFactory(application)
        )[PreferenceViewModel::class.java]

        val scheduleType = preferenceViewModel.scheduleType

        when (scheduleType) {
            TWO_IN_TWO -> launchTwoInTwoSchedule()
            DEFAULT -> launchDefaultSchedule()
            else -> launchDefaultSchedule()
        }
    }

    private fun launchTwoInTwoSchedule() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.schedule_activity_container, WorkScheduleFragment.newTwoInTwoFragment())
            .commit()
    }

    private fun launchDefaultSchedule() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.schedule_activity_container, DefaultScheduleFragment.newInstance())
            .commit()
    }

    companion object {
        private const val DEFAULT = "Default"
        private const val TWO_IN_TWO = "2 / 2"
    }
}
