package com.github.googelfist.workshedule.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.googelfist.workshedule.R

class DayViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    val day: TextView = view.findViewById(R.id.tv_calendar_cell)
}