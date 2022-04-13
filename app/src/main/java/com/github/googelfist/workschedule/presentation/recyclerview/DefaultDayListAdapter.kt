package com.github.googelfist.workschedule.presentation.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.github.googelfist.workschedule.R
import com.github.googelfist.workschedule.domain.schedulegenerator.models.Day
import com.github.googelfist.workschedule.domain.schedulegenerator.models.DefaultDay

class DefaultDayListAdapter : ListAdapter<Day, DayViewHolder>(DayDiffCallback()) {

    lateinit var onDayClickListener: ((Day) -> Unit)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val layout = when (viewType) {
            TODAY_TYPE -> R.layout.today
            INACTIVE_DAY_TYPE -> R.layout.inactive_day
            ACTIVE_DAY_TYPE -> R.layout.active_day
            else -> throw IllegalArgumentException("Unknown view type: $viewType")
        }

        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return DayViewHolder(view)
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        val day = getItem(position)
        holder.day.text = day.getDayValue().toString()
        holder.day.setOnClickListener {
            onDayClickListener.invoke(day)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val day = getItem(position)

        return when {
            day is DefaultDay && !day.isCurrentMonth() -> INACTIVE_DAY_TYPE
            day is DefaultDay && day.isToday() -> TODAY_TYPE
            day is DefaultDay && day.isCurrentMonth() -> ACTIVE_DAY_TYPE
            else -> throw NoSuchElementException("Unknown view type")
        }
    }

    companion object {
        const val ACTIVE_DAY_TYPE = 201
        const val ACTIVE_DAY_POOL_SIZE = 31

        const val INACTIVE_DAY_TYPE = 200
        const val INACTIVE_DAY_POOL_SIZE = 12

        const val TODAY_TYPE = 500
        const val TODAY_DAY_POOL_SIZE = 1
    }
}
