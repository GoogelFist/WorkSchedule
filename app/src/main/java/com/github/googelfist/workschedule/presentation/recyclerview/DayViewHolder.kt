package com.github.googelfist.workschedule.presentation.recyclerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.googelfist.workschedule.R

class DayViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val day: TextView = view.findViewById(R.id.cv_calendar_cell)
}
