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
        defaultTextView.background = null
        defaultTextView.alpha = NOT_CURRENT_MONTH_ALPHA_VALUE
        defaultTextView.text = day.getDayValue().toString()
    }

    private fun setTodayView(day: Day) {
        defaultTextView.background =
            ContextCompat.getDrawable(view.context, R.drawable.today_default_background)
        defaultTextView.alpha = FULL_ALPHA_VALUE
        defaultTextView.text = day.getDayValue().toString()
    }

    private fun setCurrentMonthView(day: Day) {
        defaultTextView.background = null
        defaultTextView.alpha = FULL_ALPHA_VALUE
        defaultTextView.text = day.getDayValue().toString()
    }

    // TODO: constant object helper
    companion object {
        private const val FULL_ALPHA_VALUE = 1f
        private const val NOT_CURRENT_MONTH_ALPHA_VALUE = 0.3f
    }
}