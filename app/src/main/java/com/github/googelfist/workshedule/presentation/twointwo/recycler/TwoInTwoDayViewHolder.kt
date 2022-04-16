package com.github.googelfist.workshedule.presentation.twointwo.recycler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.googelfist.workshedule.R
import com.github.googelfist.workshedule.domain.models.day.Day
import com.github.googelfist.workshedule.domain.models.day.WeekendDay
import com.github.googelfist.workshedule.domain.models.day.WorkDay

class TwoInTwoDayViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val notCurrentWorkDayTextView: TextView by lazy { view.findViewById(R.id.text_view_work_not_current_month_day_item) }
    private val notCurrentWeekendDayTextView: TextView by lazy { view.findViewById(R.id.text_view_weekend_not_current_month_day_item) }

    private val todayWorkDayTextView: TextView by lazy { view.findViewById(R.id.text_view_work_today_day_item) }
    private val todayWeekendDayTextView: TextView by lazy { view.findViewById(R.id.text_view_weekend_today_day_item) }

    private val currentWorkDayTextView: TextView by lazy { view.findViewById(R.id.text_view_work_current_month_day_item) }
    private val currentWeekendDayTextView: TextView by lazy { view.findViewById(R.id.text_view_weekend_current_month_day_item) }

    fun bind(day: Day) {
        when {
            day is WorkDay && !day.currentMonth -> {
                notCurrentWorkDayTextView.visibility = View.VISIBLE
                notCurrentWeekendDayTextView.visibility = View.GONE

                notCurrentWorkDayTextView.text = day.getDayValue().toString()
            }
            day is WeekendDay && !day.currentMonth -> {
                notCurrentWorkDayTextView.visibility = View.GONE
                notCurrentWeekendDayTextView.visibility = View.VISIBLE

                notCurrentWeekendDayTextView.text = day.getDayValue().toString()
            }
            day is WorkDay && day.today -> {
                todayWorkDayTextView.visibility = View.VISIBLE
                todayWeekendDayTextView.visibility = View.GONE

                todayWorkDayTextView.text = day.getDayValue().toString()
            }
            day is WeekendDay && day.today -> {
                todayWorkDayTextView.visibility = View.GONE
                todayWeekendDayTextView.visibility = View.VISIBLE

                todayWeekendDayTextView.text = day.getDayValue().toString()
            }
            day is WorkDay && day.currentMonth -> {
                currentWorkDayTextView.visibility = View.VISIBLE
                currentWeekendDayTextView.visibility = View.GONE

                currentWorkDayTextView.text = day.getDayValue().toString()
            }
            else -> {
                currentWorkDayTextView.visibility = View.GONE
                currentWeekendDayTextView.visibility = View.VISIBLE

                currentWeekendDayTextView.text = day.getDayValue().toString()
            }
        }
//        viewDay.text = day.getDayValue().toString()
    }
}