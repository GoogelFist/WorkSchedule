package com.github.googelfist.workshedule.presentation.def.recycler.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.googelfist.workshedule.R
import com.github.googelfist.workshedule.domain.models.day.Day

class DefaultDayNotCurrentMonthViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val notCurrentDefaultDayTextView: TextView by lazy(LazyThreadSafetyMode.NONE) {
        view.findViewById(R.id.text_view_default_not_current_month_day_item)
    }

    fun bind(day: Day) {
        notCurrentDefaultDayTextView.text = day.getDayValue().toString()
    }
}