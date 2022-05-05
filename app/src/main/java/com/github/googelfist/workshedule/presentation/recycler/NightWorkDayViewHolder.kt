package com.github.googelfist.workshedule.presentation.recycler

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.github.googelfist.workshedule.R
import com.github.googelfist.workshedule.domain.models.day.Day
import com.github.googelfist.workshedule.domain.models.day.NightWorkDay

class NightWorkDayViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val workDayTextView: TextView by lazy(LazyThreadSafetyMode.NONE) {
        view.findViewById(R.id.text_view_night_work_day_item)
    }

    fun bind(day: Day) {
        when {
            day is NightWorkDay && !day.currentMonth -> setNotCurrentMonthView(day)
            day is NightWorkDay && day.today -> setTodayView(day)
            day is NightWorkDay && day.currentMonth -> setCurrentMonthView(day)
            else -> throw NoSuchElementException("Unknown day type")
        }
    }

    private fun setNotCurrentMonthView(day: Day) {
        workDayTextView.setBackgroundColor(
            ContextCompat.getColor(view.context, R.color.purple_200)
        )
        workDayTextView.setTextColor(
            ContextCompat.getColor(view.context, R.color.not_current_month_text_color)
        )
        workDayTextView.text = day.getDayValue().toString()
    }

    private fun setTodayView(day: Day) {
        workDayTextView.background =
            ContextCompat.getDrawable(view.context, R.drawable.today_night_work_background)

        workDayTextView.setTextColor(ContextCompat.getColor(view.context, R.color.black))
        workDayTextView.text = day.getDayValue().toString()
    }

    private fun setCurrentMonthView(day: Day) {
        workDayTextView.setBackgroundColor(
            ContextCompat.getColor(view.context, R.color.purple_500)
        )
        workDayTextView.setTextColor(ContextCompat.getColor(view.context, R.color.black))
        workDayTextView.text = day.getDayValue().toString()
    }
}