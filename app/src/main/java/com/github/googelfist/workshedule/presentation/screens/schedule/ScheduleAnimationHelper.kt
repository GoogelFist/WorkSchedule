package com.github.googelfist.workshedule.presentation.screens.schedule

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import com.github.googelfist.workshedule.domain.models.ScheduleState
import com.github.googelfist.workshedule.presentation.screens.schedule.recycler.DayListAdapter

object ScheduleAnimationHelper {

    fun setAnimatingPreviousScheduleState(
        state: ScheduleState.GeneratedPrevious,
        rootLayout: ConstraintLayout,
        textView: TextView,
        adapter: DayListAdapter,
        navigationButtons: ConstraintLayout
    ) {
        val startX = rootLayout.x

        rootLayout.animate()
            .withStartAction { disableButtonsClickable(navigationButtons) }
            .alpha(NONE_ALPHA_PROPERTY)
            .setDuration(PREVIOUS_SCHEDULE_DURATION)
            .x(PREVIOUS_X_OUT_PROPERTY)
            .withEndAction {

                adapter.submitList(state.dayList)
                textView.text = state.formattedDate

                rootLayout.x = PREVIOUS_X_IN_PROPERTY

                rootLayout.animate()
                    .alpha(FULL_ALPHA_PROPERTY)
                    .setDuration(PREVIOUS_SCHEDULE_DURATION)
                    .x(startX)
                    .withEndAction { enableButtonsClickable(navigationButtons) }
            }
    }

    fun setAnimatingCurrentScheduleState(
        state: ScheduleState.GeneratedCurrent,
        rootLayout: ConstraintLayout,
        textView: TextView,
        adapter: DayListAdapter,
        progressBar: ProgressBar,
        navigationButtons: ConstraintLayout
    ) {

        rootLayout.animate()
            .withStartAction { disableButtonsClickable(navigationButtons) }
            .alpha(NONE_ALPHA_PROPERTY)
            .setDuration(CURRENT_SCHEDULE_DURATION)
            .withEndAction {

                progressBar.visibility = View.GONE
                rootLayout.visibility = View.VISIBLE

                adapter.submitList(state.dayList)
                textView.text = state.formattedDate

                rootLayout.animate()
                    .alpha(FULL_ALPHA_PROPERTY)
                    .setDuration(CURRENT_SCHEDULE_DURATION)
                    .withEndAction { enableButtonsClickable(navigationButtons) }
            }
    }

    fun setAnimatingNextScheduleState(
        state: ScheduleState.GeneratedNext,
        rootLayout: ConstraintLayout,
        textView: TextView,
        adapter: DayListAdapter,
        navigationButtons: ConstraintLayout
    ) {
        val startX = rootLayout.x

        rootLayout.animate()
            .withStartAction { disableButtonsClickable(navigationButtons) }
            .alpha(NONE_ALPHA_PROPERTY)
            .setDuration(NEXT_SCHEDULE_DURATION)
            .x(NEXT_X_OUT_PROPERTY)
            .withEndAction {

                adapter.submitList(state.dayList)
                textView.text = state.formattedDate

                rootLayout.x = NEXT_X_IN_PROPERTY

                rootLayout.animate()
                    .alpha(FULL_ALPHA_PROPERTY)
                    .setDuration(NEXT_SCHEDULE_DURATION)
                    .x(startX)
                    .withEndAction { enableButtonsClickable(navigationButtons) }
            }
    }
}

private fun enableButtonsClickable(navigationButtons: ConstraintLayout) {
    val buttons = navigationButtons.children
    for (button in buttons) {
        button.isClickable = true
    }
}

private fun disableButtonsClickable(navigationButtons: ConstraintLayout) {
    val buttons = navigationButtons.children
    for (button in buttons) {
        button.isClickable = false
    }
}

private const val PREVIOUS_SCHEDULE_DURATION = 300L
private const val PREVIOUS_X_OUT_PROPERTY = 100f
private const val PREVIOUS_X_IN_PROPERTY = -200f

private const val CURRENT_SCHEDULE_DURATION = 300L

private const val NEXT_SCHEDULE_DURATION = 300L
private const val NEXT_X_OUT_PROPERTY = -100f
private const val NEXT_X_IN_PROPERTY = 200f

private const val FULL_ALPHA_PROPERTY = 1f
private const val NONE_ALPHA_PROPERTY = 0f


