package com.github.googelfist.workshedule.presentation.screens.schedule.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.github.googelfist.workshedule.R
import com.github.googelfist.workshedule.domain.models.Day

class DayListAdapter : ListAdapter<Day, DayViewHolder>(DayDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {

        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.day_item, parent, false)

        return DayViewHolder(layout)
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        val day = getItem(position)

        holder.bind(day)
    }
}