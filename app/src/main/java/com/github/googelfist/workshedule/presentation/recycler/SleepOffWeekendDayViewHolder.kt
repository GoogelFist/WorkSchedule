package com.github.googelfist.workshedule.presentation.recycler

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.github.googelfist.workshedule.R
import com.github.googelfist.workshedule.domain.models.day.Day
import com.github.googelfist.workshedule.domain.models.day.SleepOffWeekendDay

class SleepOffWeekendDayViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val weekendDayTextView: TextView by lazy(LazyThreadSafetyMode.NONE) {
        view.findViewById(R.id.text_view_sleep_off_weekend_day_item)
    }

    fun bind(day: Day) {

        when {
            day is SleepOffWeekendDay && !day.currentMonth -> setNotCurrentMonthView(day)
            day is SleepOffWeekendDay && day.today -> setTodayView(day)
            day is SleepOffWeekendDay && day.currentMonth -> setCurrentMonthView(day)
            else -> throw NoSuchElementException("Unknown day type")
        }
    }

    private fun setNotCurrentMonthView(day: Day) {
        weekendDayTextView.setBackgroundColor(
            ContextCompat.getColor(view.context, R.color.teal_700)
        )
        weekendDayTextView.setTextColor(
            ContextCompat.getColor(view.context, R.color.not_current_month_text_color)
        )
        weekendDayTextView.text = day.getDayValue().toString()
    }

    private fun setTodayView(day: Day) {
        weekendDayTextView.background =
            ContextCompat.getDrawable(view.context, R.drawable.today_sleep_off_weekend_background)

        weekendDayTextView.setTextColor(ContextCompat.getColor(view.context, R.color.black))
        weekendDayTextView.text = day.getDayValue().toString()
    }

    private fun setCurrentMonthView(day: Day) {
        weekendDayTextView.setBackgroundColor(
            ContextCompat.getColor(view.context, R.color.teal_200)
        )
        weekendDayTextView.setTextColor(ContextCompat.getColor(view.context, R.color.black))
        weekendDayTextView.text = day.getDayValue().toString()
    }
}