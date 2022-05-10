package com.github.googelfist.workshedule.presentation.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.googelfist.workshedule.R
import com.github.googelfist.workshedule.domain.models.Day

class DayListAdapter : ListAdapter<Day, RecyclerView.ViewHolder>(DayDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == DAY_TYPE) {
            val layout = LayoutInflater.from(parent.context)
                .inflate(R.layout.day_item, parent, false)

            return DayViewHolder(layout)
        } else {
            throw RuntimeException("Unknown view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val day = getItem(position)

        if (holder is DayViewHolder) {
            holder.bind(day)
        } else {
            throw RuntimeException("Unknown view holder type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        val day = getItem(position)

        if (day is Day) {
            return DAY_TYPE
        } else {
            throw RuntimeException("Unknown view type")
        }
    }

    companion object {
        const val DAY_TYPE = 200

        const val DAY_TYPE_POOL_SIZE = 84
    }
}