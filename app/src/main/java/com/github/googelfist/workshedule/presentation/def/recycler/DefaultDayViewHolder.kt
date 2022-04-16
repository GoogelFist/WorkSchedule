package com.github.googelfist.workshedule.presentation.def.recycler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.googelfist.workshedule.R
import com.github.googelfist.workshedule.domain.models.day.Day
import com.github.googelfist.workshedule.domain.models.day.DefaultDay

class DefaultDayViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val notCurrentDefaultDayTextView: TextView by lazy { view.findViewById(R.id.text_view_default_not_current_month_day_item) }
    private val todayDefaultTextView: TextView by lazy { view.findViewById(R.id.text_view_default_today_day_item) }
    private val currentDefaultDayTextView: TextView by lazy { view.findViewById(R.id.text_view_default_current_month_day_item) }

    fun bind(day: Day) {
        when {
            day is DefaultDay && !day.currentMonth -> {
                notCurrentDefaultDayTextView.visibility = View.VISIBLE
                notCurrentDefaultDayTextView.text = day.getDayValue().toString()
            }
            day is DefaultDay && day.today -> {
                todayDefaultTextView.visibility = View.VISIBLE
                todayDefaultTextView.text = day.getDayValue().toString()
            }
            else -> {
                currentDefaultDayTextView.visibility = View.VISIBLE
                currentDefaultDayTextView.text = day.getDayValue().toString()
            }
        }
//        viewDay.text = day.getDayValue().toString()
    }
}