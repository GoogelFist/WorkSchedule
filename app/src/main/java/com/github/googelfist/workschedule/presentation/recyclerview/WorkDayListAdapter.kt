package com.github.googelfist.workschedule.presentation.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.github.googelfist.workschedule.R
import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.models.Day
import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.models.workday.WorkActiveDay
import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.models.workday.WorkInActiveDay
import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.models.workday.WorkToday

class WorkDayListAdapter : ListAdapter<Day, DayViewHolder>(DayDiffCallback()) {

    lateinit var onDayClickListener: ((Day) -> Unit)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val layout = when (viewType) {
            TODAY_WORK_TYPE -> R.layout.today_work
            TODAY_WEEKEND_TYPE -> R.layout.today_weekend

            ACTIVE_WORK_DAY_TYPE -> R.layout.active_work_day
            INACTIVE_WORK_DAY_TYPE -> R.layout.inactive_work_day

            ACTIVE_WEEKEND_DAY_TYPE -> R.layout.active_weekend_day
            INACTIVE_WEEKEND_DAY_TYPE -> R.layout.inactive_weekend_day

            else -> throw IllegalArgumentException("Unknown view type: $viewType")
        }

        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return DayViewHolder(view)
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        val day = getItem(position)
        holder.day.text = day.value.toString()
        holder.day.setOnClickListener {
            onDayClickListener.invoke(day)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val day = getItem(position)

        return when {
            day is WorkToday && day.isWork -> TODAY_WORK_TYPE
            day is WorkToday && day.isWeekend -> TODAY_WEEKEND_TYPE

            day is WorkActiveDay && day.isWork -> ACTIVE_WORK_DAY_TYPE
            day is WorkInActiveDay && day.isWork -> INACTIVE_WORK_DAY_TYPE

            day is WorkActiveDay && day.isWeekend -> ACTIVE_WEEKEND_DAY_TYPE
            day is WorkInActiveDay && day.isWeekend -> INACTIVE_WEEKEND_DAY_TYPE

            else -> throw NoSuchElementException("Unknown view type")
        }
    }

    companion object {

        const val ACTIVE_WEEKEND_DAY_TYPE = 301
        const val ACTIVE_WEEKEND_DAY_POOL_SIZE = 15

        const val INACTIVE_WEEKEND_DAY_TYPE = 300
        const val INACTIVE_WEEKEND_DAY_POOL_SIZE = 6

        const val ACTIVE_WORK_DAY_TYPE = 401
        const val ACTIVE_WORK_DAY_POOL_SIZE = 15

        const val INACTIVE_WORK_DAY_TYPE = 400
        const val INACTIVE_WORK_DAY_POOL_SIZE = 6

        const val TODAY_WEEKEND_TYPE = 501
        const val TODAY_WEEKEND_DAY_POOL_SIZE = 1

        const val TODAY_WORK_TYPE = 502
        const val TODAY_WORK_DAY_POOL_SIZE = 1
    }
}
