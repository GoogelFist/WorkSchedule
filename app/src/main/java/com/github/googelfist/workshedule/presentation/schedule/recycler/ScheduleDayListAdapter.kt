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
import com.github.googelfist.workshedule.presentation.schedule.recycler.holder.CurrentMonthWeekendDayViewHolder
import com.github.googelfist.workshedule.presentation.schedule.recycler.holder.CurrentMonthWorkDayViewHolder
import com.github.googelfist.workshedule.presentation.schedule.recycler.holder.NotCurrentMonthWeekendDayViewHolder
import com.github.googelfist.workshedule.presentation.schedule.recycler.holder.NotCurrentMonthWorkDayViewHolder
import com.github.googelfist.workshedule.presentation.schedule.recycler.holder.TodayWeekendViewHolder
import com.github.googelfist.workshedule.presentation.schedule.recycler.holder.TodayWorkViewHolder

class ScheduleDayListAdapter : ListAdapter<Day, RecyclerView.ViewHolder>(DayDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        when (viewType) {
            NOT_CURRENT_MONTH_WORK_DAY_TYPE -> {
                val layout = LayoutInflater.from(parent.context)
                    .inflate(R.layout.work_day_item_not_current_month, parent, false)
                return NotCurrentMonthWorkDayViewHolder(layout)
            }
            NOT_CURRENT_MONTH_WEEKEND_DAY_TYPE -> {
                val layout = LayoutInflater.from(parent.context)
                    .inflate(R.layout.weekend_day_itemt_not_current_month, parent, false)
                return NotCurrentMonthWeekendDayViewHolder(layout)
            }
            TODAY_WORK_TYPE -> {
                val layout = LayoutInflater.from(parent.context)
                    .inflate(R.layout.work_day_itemt_today, parent, false)
                return TodayWorkViewHolder(layout)
            }
            TODAY_WEEKEND_TYPE -> {
                val layout = LayoutInflater.from(parent.context)
                    .inflate(R.layout.weekend_day_itemt_today, parent, false)
                return TodayWeekendViewHolder(layout)
            }
            CURRENT_MONTH_WORK_DAY_TYPE -> {
                val layout = LayoutInflater.from(parent.context)
                    .inflate(R.layout.work_day_item_current_month, parent, false)
                return CurrentMonthWorkDayViewHolder(layout)
            }
            CURRENT_MONTH_WEEKEND_DAY_TYPE -> {
                val layout = LayoutInflater.from(parent.context)
                    .inflate(R.layout.weekend_day_item_current_month, parent, false)
                return CurrentMonthWeekendDayViewHolder(layout)
            }
            else -> throw IllegalArgumentException("Unknown view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val day = getItem(position)
        when (holder) {
            is NotCurrentMonthWorkDayViewHolder -> holder.bind(day)
            is NotCurrentMonthWeekendDayViewHolder -> holder.bind(day)
            is TodayWorkViewHolder -> holder.bind(day)
            is TodayWeekendViewHolder -> holder.bind(day)
            is CurrentMonthWorkDayViewHolder -> holder.bind(day)
            is CurrentMonthWeekendDayViewHolder -> holder.bind(day)
            else -> throw NoSuchElementException("Unknown view holder type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        val day = getItem(position)

        return when {
            day is WorkDay && day.isToday() -> TODAY_WORK_TYPE
            day is WeekendDay && day.isToday() -> TODAY_WEEKEND_TYPE

            day is WorkDay && day.isCurrentMonth() -> CURRENT_MONTH_WORK_DAY_TYPE
            day is WorkDay && !day.isCurrentMonth() -> NOT_CURRENT_MONTH_WORK_DAY_TYPE

            day is WeekendDay && day.isCurrentMonth() -> CURRENT_MONTH_WEEKEND_DAY_TYPE
            day is WeekendDay && !day.isCurrentMonth() -> NOT_CURRENT_MONTH_WEEKEND_DAY_TYPE

            else -> throw NoSuchElementException("Unknown view type")
        }
    }

    companion object {
        const val CURRENT_MONTH_WEEKEND_DAY_TYPE = 301
        const val ACTIVE_WEEKEND_DAY_POOL_SIZE = 15

        const val NOT_CURRENT_MONTH_WEEKEND_DAY_TYPE = 300
        const val INACTIVE_WEEKEND_DAY_POOL_SIZE = 6

        const val CURRENT_MONTH_WORK_DAY_TYPE = 401
        const val ACTIVE_WORK_DAY_POOL_SIZE = 15

        const val NOT_CURRENT_MONTH_WORK_DAY_TYPE = 400
        const val INACTIVE_WORK_DAY_POOL_SIZE = 6

        const val TODAY_WEEKEND_TYPE = 501
        const val TODAY_WEEKEND_DAY_POOL_SIZE = 1

        const val TODAY_WORK_TYPE = 502
        const val TODAY_WORK_DAY_POOL_SIZE = 1
    }
}