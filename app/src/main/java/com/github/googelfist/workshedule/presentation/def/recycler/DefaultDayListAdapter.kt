package com.github.googelfist.workshedule.presentation.def.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.googelfist.workshedule.R
import com.github.googelfist.workshedule.domain.models.day.Day
import com.github.googelfist.workshedule.domain.models.day.DefaultDay
import com.github.googelfist.workshedule.presentation.DayDiffCallback
import com.github.googelfist.workshedule.presentation.def.recycler.holder.DefaultDayCurrentMonthViewHolder
import com.github.googelfist.workshedule.presentation.def.recycler.holder.DefaultDayNotCurrentMonthViewHolder
import com.github.googelfist.workshedule.presentation.def.recycler.holder.DefaultDayTodayViewHolder

class DefaultDayListAdapter : ListAdapter<Day, RecyclerView.ViewHolder>(DayDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        when (viewType) {
            NOT_CURRENT_MONTH_DAY_TYPE -> {
                val layout = LayoutInflater.from(parent.context)
                    .inflate(R.layout.default_day_item_not_current_month, parent, false)
                return DefaultDayNotCurrentMonthViewHolder(layout)
            }

            TODAY_TYPE -> {
                val layout = LayoutInflater.from(parent.context)
                    .inflate(R.layout.default_day_itemt_today, parent, false)
                return DefaultDayTodayViewHolder(layout)
            }

            CURRENT_MONTH_DAY_TYPE -> {
                val layout = LayoutInflater.from(parent.context)
                    .inflate(R.layout.default_day_item_current_month, parent, false)
                return DefaultDayCurrentMonthViewHolder(layout)
            }

            else -> {
                throw NoSuchElementException("Unknown view type: $viewType")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val day = getItem(position)
        when (holder) {
            is DefaultDayNotCurrentMonthViewHolder -> holder.bind(day)
            is DefaultDayTodayViewHolder -> holder.bind(day)
            is DefaultDayCurrentMonthViewHolder -> holder.bind(day)
            else -> throw NoSuchElementException("Unknown view holder type")
        }
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