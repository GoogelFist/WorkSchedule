package com.github.googelfist.workshedule.presentation.schedule.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.googelfist.workshedule.R
import com.github.googelfist.workshedule.domain.models.day.Day
import com.github.googelfist.workshedule.domain.models.day.WeekendDay
import com.github.googelfist.workshedule.domain.models.day.WorkDay
import com.github.googelfist.workshedule.presentation.DayDiffCallback
import com.github.googelfist.workshedule.presentation.schedule.recycler.holder.WeekendViewHolder
import com.github.googelfist.workshedule.presentation.schedule.recycler.holder.WorkViewHolder

class ScheduleDayListAdapter : ListAdapter<Day, RecyclerView.ViewHolder>(DayDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            WEEKEND_TYPE -> {
                val layout = LayoutInflater.from(parent.context)
                    .inflate(R.layout.weekend_day_item, parent, false)
                WeekendViewHolder(layout)
            }
            WORK_TYPE -> {
                val layout = LayoutInflater.from(parent.context)
                    .inflate(R.layout.work_day_item, parent, false)
                WorkViewHolder(layout)
            }
            else -> throw IllegalArgumentException("Unknown view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val day = getItem(position)
        when (holder) {
            is WorkViewHolder -> holder.bind(day)
            is WeekendViewHolder -> holder.bind(day)
            else -> throw NoSuchElementException("Unknown view holder type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        val day = getItem(position)

        return when (day) {
            is WorkDay -> WORK_TYPE
            is WeekendDay -> WEEKEND_TYPE
            else -> throw NoSuchElementException("Unknown view type")
        }
    }

    companion object {
        private const val WORK_TYPE = 300
        private const val WEEKEND_TYPE = 400
    }
}