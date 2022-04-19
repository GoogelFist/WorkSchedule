package com.github.googelfist.workshedule.presentation.schedule.recycler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.googelfist.workshedule.R
import com.github.googelfist.workshedule.domain.models.day.Day

class TodayWeekendViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val todayWeekendDayTextView: TextView by lazy(LazyThreadSafetyMode.NONE) {
        view.findViewById(R.id.text_view_weekend_today_day_item)
    }

    fun bind(day: Day) {
        todayWeekendDayTextView.text = day.getDayValue().toString()
    }
}