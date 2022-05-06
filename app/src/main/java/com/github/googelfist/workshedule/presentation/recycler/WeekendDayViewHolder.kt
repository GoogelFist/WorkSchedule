package com.github.googelfist.workshedule.presentation.recycler

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.github.googelfist.workshedule.R
import com.github.googelfist.workshedule.domain.models.day.Day
import com.github.googelfist.workshedule.domain.models.day.WeekendDay

class WeekendDayViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val weekendDayConstraintLayout: ConstraintLayout by lazy(LazyThreadSafetyMode.NONE) {
        view.findViewById(R.id.weekend_day_cl)
    }
    private val weekendDayTextView: TextView by lazy(LazyThreadSafetyMode.NONE) {
        view.findViewById(R.id.text_view_weekend_day_item)
    }

    fun bind(day: Day) {

        when {
            day is WeekendDay && !day.currentMonth -> setNotCurrentMonthView(day)
            day is WeekendDay && day.today -> setTodayView(day)
            day is WeekendDay && day.currentMonth -> setCurrentMonthView(day)
            else -> throw NoSuchElementException("Unknown day type")
        }
    }

    private fun setNotCurrentMonthView(day: Day) {
        weekendDayConstraintLayout.alpha = NOT_CURRENT_MONTH_ALPHA_VALUE
        weekendDayConstraintLayout.setBackgroundColor(
            ContextCompat.getColor(
                view.context,
                R.color.weekend_day
            )
        )

        weekendDayTextView.background = null
        weekendDayTextView.text = day.getDayValue().toString()
    }

    private fun setTodayView(day: Day) {
        weekendDayConstraintLayout.alpha = FULL_ALPHA_VALUE
        weekendDayConstraintLayout.setBackgroundColor(
            ContextCompat.getColor(
                view.context,
                R.color.weekend_day
            )
        )

        weekendDayTextView.background =
            ContextCompat.getDrawable(view.context, R.drawable.today_weekend_background)
        weekendDayTextView.text = day.getDayValue().toString()
    }

    private fun setCurrentMonthView(day: Day) {
        weekendDayConstraintLayout.alpha = FULL_ALPHA_VALUE
        weekendDayConstraintLayout.setBackgroundColor(
            ContextCompat.getColor(
                view.context,
                R.color.weekend_day
            )
        )

        weekendDayTextView.background = null
        weekendDayTextView.text = day.getDayValue().toString()
    }

    // TODO: constant object helper
    companion object {
        private const val FULL_ALPHA_VALUE = 1f
        private const val NOT_CURRENT_MONTH_ALPHA_VALUE = 0.3f
    }
}