package com.github.googelfist.workshedule.presentation.schedule.recycler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.googelfist.workshedule.R
import com.github.googelfist.workshedule.domain.models.day.Day

class CurrentMonthWeekendDayViewHolder(private val view: View) :
    RecyclerView.ViewHolder(view) {

    private val currentWeekendDayTextView: TextView by lazy(LazyThreadSafetyMode.NONE) {
        view.findViewById(R.id.text_view_weekend_current_month_day_item)
    }

    fun bind(day: Day) {
        currentWeekendDayTextView.text = day.getDayValue().toString()
    }
}