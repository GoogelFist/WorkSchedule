package com.github.googelfist.workshedule.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.github.googelfist.workshedule.R
import com.github.googelfist.workshedule.domain.models.Day

class DayListAdapter : ListAdapter<Day, DayViewHolder>(DayDiffCallback()) {

    lateinit var onDayClickListener: ((Day) -> Unit)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val layout = when (viewType) {
            TODAY_WORK_TYPE -> R.layout.today_work
            TODAY_WEEKEND_TYPE -> R.layout.today_weekend
            TODAY_TYPE -> R.layout.today

            ACTIVE_WORK_DAY_TYPE -> R.layout.active_work_day
            INACTIVE_WORK_DAY_TYPE -> R.layout.inactive_work_day

            ACTIVE_WEEKEND_DAY_TYPE -> R.layout.active_weekend_day
            INACTIVE_WEEKEND_DAY_TYPE -> R.layout.inactive_weekend_day

            INACTIVE_DAY_TYPE -> R.layout.inactive_day
            ACTIVE_DAY_TYPE -> R.layout.active_day

            else -> throw RuntimeException("Unknown view type: $viewType")
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
            day.isActive && day.isWork && day.isToday -> TODAY_WORK_TYPE
            day.isActive && day.isWeekend && day.isToday -> TODAY_WEEKEND_TYPE
            day.isToday && day.isActive -> TODAY_TYPE

            day.isWork && day.isActive -> ACTIVE_WORK_DAY_TYPE
            day.isWork && !day.isActive -> INACTIVE_WORK_DAY_TYPE

            day.isWeekend && day.isActive -> ACTIVE_WEEKEND_DAY_TYPE
            day.isWeekend && !day.isActive -> INACTIVE_WEEKEND_DAY_TYPE

            !day.isActive -> INACTIVE_DAY_TYPE

            else -> ACTIVE_DAY_TYPE
        }
    }

    companion object {
        const val ACTIVE_DAY_TYPE = 201
        const val INACTIVE_DAY_TYPE = 200

        const val ACTIVE_WEEKEND_DAY_TYPE = 301
        const val INACTIVE_WEEKEND_DAY_TYPE = 300

        const val ACTIVE_WORK_DAY_TYPE = 401
        const val INACTIVE_WORK_DAY_TYPE = 400

        const val TODAY_TYPE = 500
        const val TODAY_WEEKEND_TYPE = 501
        const val TODAY_WORK_TYPE = 502

        const val ACTIVE_DAY_POOL_SIZE = 31
        const val INACTIVE_DAY_POOL_SIZE = 14
        const val TODAY_DAY_POOL_SIZE = 1
    }
}
