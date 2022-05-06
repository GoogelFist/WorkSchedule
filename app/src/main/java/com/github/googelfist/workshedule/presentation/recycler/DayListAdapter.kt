package com.github.googelfist.workshedule.presentation.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.googelfist.workshedule.R
import com.github.googelfist.workshedule.domain.models.day.Day
import com.github.googelfist.workshedule.domain.models.day.DefaultDay
import com.github.googelfist.workshedule.domain.models.day.WeekendDay
import com.github.googelfist.workshedule.domain.models.day.WeekendDaySleepOff
import com.github.googelfist.workshedule.domain.models.day.WorkDay
import com.github.googelfist.workshedule.domain.models.day.WorkNight

class DayListAdapter : ListAdapter<Day, RecyclerView.ViewHolder>(DayDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            DEFAULT_DAY_TYPE -> {
                val layout = LayoutInflater.from(parent.context)
                    .inflate(R.layout.default_day_item, parent, false)
                return DefaultDayViewHolder(layout)
            }
            WEEKEND_DAY_TYPE -> {
                val layout = LayoutInflater.from(parent.context)
                    .inflate(R.layout.weekend_day_item, parent, false)
                WeekendDayViewHolder(layout)
            }
            SLEEP_OFF_WEEKEND_DAY_TYPE -> {
                val layout = LayoutInflater.from(parent.context)
                    .inflate(R.layout.weekend_sleep_off_day_item, parent, false)
                WeekendSleepOffDayViewHolder(layout)
            }
            WORK_DAY_TYPE -> {
                val layout = LayoutInflater.from(parent.context)
                    .inflate(R.layout.work_day_item, parent, false)
                WorkDayViewHolder(layout)
            }
            NIGHT_WORK_DAY_TYPE -> {
                val layout = LayoutInflater.from(parent.context)
                    .inflate(R.layout.work_night_item, parent, false)
                WorkNightViewHolder(layout)
            }
            else -> throw IllegalArgumentException("Unknown view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val day = getItem(position)
        when (holder) {
            is DefaultDayViewHolder -> holder.bind(day)
            is WorkDayViewHolder -> holder.bind(day)
            is WorkNightViewHolder -> holder.bind(day)
            is WeekendDayViewHolder -> holder.bind(day)
            is WeekendSleepOffDayViewHolder -> holder.bind(day)
            else -> throw NoSuchElementException("Unknown view holder type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        val day = getItem(position)

        return when (day) {
            is DefaultDay -> DEFAULT_DAY_TYPE
            is WorkDay -> WORK_DAY_TYPE
            is WorkNight -> NIGHT_WORK_DAY_TYPE
            is WeekendDay -> WEEKEND_DAY_TYPE
            is WeekendDaySleepOff -> SLEEP_OFF_WEEKEND_DAY_TYPE
            else -> throw NoSuchElementException("Unknown view type")
        }
    }

    companion object {
        const val DEFAULT_DAY_TYPE = 200

        const val WORK_DAY_TYPE = 300
        const val NIGHT_WORK_DAY_TYPE = 350

        const val WEEKEND_DAY_TYPE = 400
        const val SLEEP_OFF_WEEKEND_DAY_TYPE = 450


        const val DEFAULT_DAY_TYPE_POOL_SIZE = 84

        const val WORK_DAY_TYPE_POOL_SIZE = 21
        const val NIGHT_WORK_TYPE_POOL_SIZE = 21

        const val WEEKEND_DAY_TYPE_POOL_SIZE = 21
        const val SLEEP_OFF_WEEKEND_TYPE_POOL_SIZE = 21
    }
}