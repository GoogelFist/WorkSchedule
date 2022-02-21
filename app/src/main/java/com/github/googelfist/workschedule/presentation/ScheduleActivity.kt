package com.github.googelfist.workschedule.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.github.googelfist.workschedule.R
import com.github.googelfist.workschedule.component
import com.github.googelfist.workschedule.presentation.viewmodel.PreferenceViewModel
import com.github.googelfist.workschedule.presentation.viewmodel.factory.PreferenceViewModelFactory
import com.github.googelfist.workschedule.presentation.viewpager.DefaultSchedulePagerFragment
import com.github.googelfist.workschedule.presentation.viewpager.WorkSchedulePagerFragment
import javax.inject.Inject

class ScheduleActivity : AppCompatActivity() {

    @Inject
    lateinit var preferenceViewModelFactory: PreferenceViewModelFactory

    private val preferenceViewModel: PreferenceViewModel by lazy {
        LazyThreadSafetyMode.NONE
        ViewModelProvider(
            this,
            preferenceViewModelFactory
        )[PreferenceViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.schedule_activity_container)

        preferenceViewModel.onScheduleTypeChange().observe(this) {
            when (it) {
                DEFAULT -> launchDefaultSchedule()
                TWO_IN_TWO -> launchTwoInTwoSchedule()
                else -> launchDefaultSchedule()
            }
        }
    }

    private fun launchTwoInTwoSchedule() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.schedule_activity_container, WorkSchedulePagerFragment.newInstance())
            .commit()
    }

    private fun launchDefaultSchedule() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.schedule_activity_container, DefaultSchedulePagerFragment.newInstance())
            .commit()
    }

    companion object {
        private const val DEFAULT = "Default"
        private const val TWO_IN_TWO = "2 / 2"
    }
}
