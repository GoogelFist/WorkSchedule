package com.github.googelfist.workshedule.presentation.schedule.recycler.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.googelfist.workshedule.R
import com.github.googelfist.workshedule.domain.models.day.Day

class CurrentMonthWorkDayViewHolder(private val view: View) :
    RecyclerView.ViewHolder(view) {

    private val currentWorkDayTextView: TextView by lazy(LazyThreadSafetyMode.NONE) {
        view.findViewById(R.id.text_view_work_current_month_day_item)
    }

    fun bind(day: Day) {
        currentWorkDayTextView.text = day.getDayValue().toString()
    }
}