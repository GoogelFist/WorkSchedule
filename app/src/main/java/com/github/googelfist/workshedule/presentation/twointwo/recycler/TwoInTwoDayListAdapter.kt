package com.github.googelfist.workshedule.presentation.twointwo.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.github.googelfist.workshedule.R
import com.github.googelfist.workshedule.domain.models.day.Day
import com.github.googelfist.workshedule.domain.models.day.WeekendDay
import com.github.googelfist.workshedule.domain.models.day.WorkDay
import com.github.googelfist.workshedule.presentation.DayDiffCallback

class TwoInTwoDayListAdapter : ListAdapter<Day, TwoInTwoDayViewHolder>(DayDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TwoInTwoDayViewHolder {
        val layout = when (viewType) {
            TODAY_WORK_TYPE -> R.layout.day_itemt_today
            TODAY_WEEKEND_TYPE -> R.layout.day_itemt_today

            CURRENT_MONTH_WORK_DAY_TYPE -> R.layout.day_item_current_month
            NOT_CURRENT_MONTH_WORK_DAY_TYPE -> R.layout.day_itemt_not_current_month

            CURRENT_MONTH_WEEKEND_DAY_TYPE -> R.layout.day_item_current_month
            NOT_CURRENT_MONTH_WEEKEND_DAY_TYPE -> R.layout.day_itemt_not_current_month

            else -> throw IllegalArgumentException("Unknown view type: $viewType")
        }

        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return TwoInTwoDayViewHolder(view)
    }

    override fun onBindViewHolder(holderDefault: TwoInTwoDayViewHolder, position: Int) {
        val day = getItem(position)
        holderDefault.bind(day)
//        holder.day.setOnClickListener {
//            onDayClickListener.invoke(day)
//        }
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