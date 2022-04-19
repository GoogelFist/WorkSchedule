package com.github.googelfist.workshedule.presentation.def.recycler.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.googelfist.workshedule.R
import com.github.googelfist.workshedule.domain.models.day.Day

class DefaultDayTodayViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val todayDefaultTextView: TextView by lazy(LazyThreadSafetyMode.NONE) {
        view.findViewById(R.id.text_view_default_today_day_item)
    }

    fun bind(day: Day) {
        todayDefaultTextView.text = day.getDayValue().toString()
    }
}