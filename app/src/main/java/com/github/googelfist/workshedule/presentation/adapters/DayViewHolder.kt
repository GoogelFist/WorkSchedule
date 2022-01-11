package com.github.googelfist.workshedule.presentation.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.googelfist.workshedule.R

class DayViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val day: TextView = view.findViewById(R.id.tv_calendar_cell)
}
