package com.github.googelfist.workshedule.presentation.schedule.recycler.holder

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.github.googelfist.workshedule.R
import com.github.googelfist.workshedule.domain.models.day.Day
import com.github.googelfist.workshedule.domain.models.day.WorkDay

class WorkViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val workDayTextView: TextView by lazy(LazyThreadSafetyMode.NONE) {
        view.findViewById(R.id.text_view_work_day_item)
    }

    fun bind(day: Day) {
        when {
            day is WorkDay && !day.currentMonth -> {
                workDayTextView.setBackgroundColor(
                    ContextCompat.getColor(
                        view.context,
                        R.color.work_not_current_month
                    )
                )
            }
            day is WorkDay && day.today -> {
                workDayTextView.background =
                    ContextCompat.getDrawable(view.context, R.drawable.today_work_background)
            }
            day is WorkDay && day.currentMonth -> {
                workDayTextView.setBackgroundColor(
                    ContextCompat.getColor(
                        view.context,
                        R.color.work_current_month
                    )
                )
            }
        }
        workDayTextView.text = day.getDayValue().toString()
    }
}