package com.github.googelfist.workshedule.presentation.schedule.recycler.holder

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.github.googelfist.workshedule.R
import com.github.googelfist.workshedule.domain.models.day.Day
import com.github.googelfist.workshedule.domain.models.day.WeekendDay

class WeekendViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val weekendDayTextView: TextView by lazy(LazyThreadSafetyMode.NONE) {
        view.findViewById(R.id.text_view_weekend_day_item)
    }

    fun bind(day: Day) {

        when {
            day is WeekendDay && !day.currentMonth -> {
                weekendDayTextView.setBackgroundColor(
                    ContextCompat.getColor(
                        view.context,
                        R.color.weekend_not_current_month
                    )
                )
            }
            day is WeekendDay && day.today -> {
                weekendDayTextView.background =
                    ContextCompat.getDrawable(view.context, R.drawable.today_weekend_background)
            }
            day is WeekendDay && day.currentMonth -> {
                weekendDayTextView.setBackgroundColor(
                    ContextCompat.getColor(
                        view.context,
                        R.color.weekend_current_month
                    )
                )
            }
        }

        weekendDayTextView.text = day.getDayValue().toString()
    }
}