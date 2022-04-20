package com.github.googelfist.workshedule.presentation.recycler

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.github.googelfist.workshedule.R
import com.github.googelfist.workshedule.domain.models.day.Day
import com.github.googelfist.workshedule.domain.models.day.DefaultDay

class DefaultDayViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val defaultTextView: TextView by lazy(LazyThreadSafetyMode.NONE) {
        view.findViewById(R.id.text_view_default_day_item)
    }

    fun bind(day: Day) {
        when {
            day is DefaultDay && !day.currentMonth -> setNotCurrentMonthView(day)
            day is DefaultDay && day.today -> setTodayView(day)
            day is DefaultDay && day.currentMonth -> setCurrentMonthView(day)
            else -> throw NoSuchElementException("Unknown day type")
        }
    }

    private fun setNotCurrentMonthView(day: Day) {
        defaultTextView.setTextColor(
            ContextCompat.getColor(view.context, R.color.not_current_month_text_color)
        )
        defaultTextView.text = day.getDayValue().toString()
    }

    private fun setTodayView(day: Day) {
        defaultTextView.background =
            ContextCompat.getDrawable(view.context, R.drawable.today_default_background)

        defaultTextView.setTextColor(ContextCompat.getColor(view.context, R.color.black))
        defaultTextView.text = day.getDayValue().toString()
    }

    private fun setCurrentMonthView(day: Day) {
        defaultTextView.setTextColor(ContextCompat.getColor(view.context, R.color.black))
        defaultTextView.text = day.getDayValue().toString()
    }
}