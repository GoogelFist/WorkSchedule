package com.github.googelfist.workshedule.presentation.screens.schedule

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.github.googelfist.workshedule.domain.models.ScheduleState
import com.github.googelfist.workshedule.presentation.screens.schedule.recycler.DayListAdapter

object ScheduleAnimationHelper {

    fun setAnimatingPreviousScheduleState(
        state: ScheduleState.GeneratedPrevious,
        rootLayout: ConstraintLayout,
        textView: TextView,
        adapter: DayListAdapter
    ) {
        val startX = rootLayout.x

        rootLayout.animate()
            .alpha(0f)
            .setDuration(DURATION)
            .x(200f)
            .withEndAction {

                adapter.submitList(state.dayList)
                textView.text = state.formattedDate

                rootLayout.x = -200f

                rootLayout.animate()
                    .alpha(1f)
                    .setDuration(DURATION)
                    .x(startX)
            }
    }

    fun setAnimatingCurrentScheduleState(
        state: ScheduleState.GeneratedCurrent,
        rootLayout: ConstraintLayout,
        textView: TextView,
        adapter: DayListAdapter,
        progressBar: ProgressBar
    ) {

        rootLayout.animate()
            .alpha(0f)
            .setDuration(250)
            .withEndAction {

                progressBar.visibility = View.GONE
                rootLayout.visibility = View.VISIBLE

                adapter.submitList(state.dayList)
                textView.text = state.formattedDate

                rootLayout.animate()
                    .alpha(1f)
                    .setDuration(250)
            }
    }

    fun setAnimatingNextScheduleState(
        state: ScheduleState.GeneratedNext,
        rootLayout: ConstraintLayout,
        textView: TextView,
        adapter: DayListAdapter
    ) {
        val startX = rootLayout.x

        rootLayout.animate()
            .alpha(0f)
            .setDuration(DURATION)
            .x(-200f)
            .withEndAction {

                adapter.submitList(state.dayList)
                textView.text = state.formattedDate

                rootLayout.x = 200f

                rootLayout.animate()
                    .alpha(1f)
                    .setDuration(DURATION)
                    .x(startX)
            }
    }
}

private const val DURATION = 500L