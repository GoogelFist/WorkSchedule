package com.github.googelfist.workshedule.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.github.googelfist.workshedule.R
import com.github.googelfist.workshedule.domain.models.Day
import com.github.googelfist.workshedule.domain.models.DefaultDay

class DefaultDayListAdapter : ListAdapter<Day, DayViewHolder>(DayDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val layout = when (viewType) {
            TODAY_TYPE -> R.layout.day_item_default_today
            NOT_CURRENT_MONTH_DAY_TYPE -> R.layout.day_item_default_not_current_month
            CURRENT_MONTH_DAY_TYPE -> R.layout.day_item_default_current_month
            else -> throw IllegalArgumentException("Unknown view type: $viewType")
        }

        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return DayViewHolder(view)
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        val day = getItem(position)
        holder.day.text = day.getDayValue().toString()
//        holder.day.setOnClickListener {
//            onDayClickListener.invoke(day)
//        }
    }

    override fun getItemViewType(position: Int): Int {
        val day = getItem(position)

        return when {
            day is DefaultDay && !day.isCurrentMonth() -> NOT_CURRENT_MONTH_DAY_TYPE
            day is DefaultDay && day.isToday() -> TODAY_TYPE
            day is DefaultDay && day.isCurrentMonth() -> CURRENT_MONTH_DAY_TYPE
            else -> throw NoSuchElementException("Unknown view type")
        }
    }

    companion object {
        const val CURRENT_MONTH_DAY_TYPE = 201
        const val ACTIVE_DAY_POOL_SIZE = 31

        const val NOT_CURRENT_MONTH_DAY_TYPE = 200
        const val INACTIVE_DAY_POOL_SIZE = 12

        const val TODAY_TYPE = 500
        const val TODAY_DAY_POOL_SIZE = 1
    }
}