package com.github.googelfist.workshedule.presentation.def.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.googelfist.workshedule.R
import com.github.googelfist.workshedule.domain.models.day.Day
import com.github.googelfist.workshedule.domain.models.day.DefaultDay
import com.github.googelfist.workshedule.presentation.DayDiffCallback
import com.github.googelfist.workshedule.presentation.def.recycler.holder.DefaultDayViewHolder

class DefaultDayListAdapter : ListAdapter<Day, RecyclerView.ViewHolder>(DayDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        when (viewType) {
            DEFAULT_DAY_TYPE -> {
                val layout = LayoutInflater.from(parent.context)
                    .inflate(R.layout.default_day_item, parent, false)
                return DefaultDayViewHolder(layout)
            }
            else -> {
                throw NoSuchElementException("Unknown view type: $viewType")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val day = getItem(position)
        when (holder) {
            is DefaultDayViewHolder -> holder.bind(day)
            else -> throw NoSuchElementException("Unknown view holder type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        val day = getItem(position)

        return when (day) {
            is DefaultDay -> DEFAULT_DAY_TYPE
            else -> throw NoSuchElementException("Unknown view type")
        }
    }

    companion object {
        const val DEFAULT_DAY_TYPE = 201
    }
}