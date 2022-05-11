package com.github.googelfist.workshedule.presentation.schedule.recycler

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.github.googelfist.workshedule.R
import com.github.googelfist.workshedule.domain.models.Day

class DayViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {


    private val constraintLayout: ConstraintLayout by lazy(LazyThreadSafetyMode.NONE) {
        view.findViewById(R.id.day_item_cl)
    }
    private val dayTextViewTitle: TextView by lazy(LazyThreadSafetyMode.NONE) {
        view.findViewById(R.id.tv_day_item_title)
    }
    private val dayTextViewValue: TextView by lazy(LazyThreadSafetyMode.NONE) {
        view.findViewById(R.id.tv_day_item_value)
    }

    fun bind(day: Day) {
        when {
            !day.currentMonth -> setNotCurrentMonthView(day)
            day.today -> setTodayView(day)
            day.currentMonth -> setCurrentMonthView(day)
            else -> throw RuntimeException("Unknown day type")
        }
    }

    private fun setNotCurrentMonthView(day: Day) {
        constraintLayout.setBackgroundColor(Color.parseColor(day.backgroundColor))
        constraintLayout.alpha = NOT_CURRENT_MONTH_ALPHA_VALUE

        dayTextViewTitle.text = day.title

        dayTextViewValue.background = null
        dayTextViewValue.text = day.day.toString()
    }

    private fun setTodayView(day: Day) {
        constraintLayout.setBackgroundColor(Color.parseColor(day.backgroundColor))
        constraintLayout.alpha = FULL_ALPHA_VALUE

        dayTextViewTitle.text = day.title

        dayTextViewValue.background =
            ContextCompat.getDrawable(view.context, R.drawable.today_background)
        dayTextViewValue.text = day.day.toString()
    }

    private fun setCurrentMonthView(day: Day) {
        constraintLayout.setBackgroundColor(Color.parseColor(day.backgroundColor))
        constraintLayout.alpha = FULL_ALPHA_VALUE

        dayTextViewTitle.text = day.title

        dayTextViewValue.background = null
        dayTextViewValue.text = day.day.toString()
    }

    // TODO: constant object helper
    companion object {
        private const val FULL_ALPHA_VALUE = 1f
        private const val NOT_CURRENT_MONTH_ALPHA_VALUE = 0.3f
    }
}