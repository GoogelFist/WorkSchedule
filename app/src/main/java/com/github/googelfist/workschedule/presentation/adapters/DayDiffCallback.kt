package com.github.googelfist.workschedule.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.github.googelfist.workschedule.domain.models.days.Day

class DayDiffCallback : DiffUtil.ItemCallback<Day>() {
    override fun areItemsTheSame(oldItem: Day, newItem: Day): Boolean {
        return oldItem.value == newItem.value &&
            oldItem.month == newItem.month &&
            oldItem.year == newItem.year
    }

    override fun areContentsTheSame(oldItem: Day, newItem: Day): Boolean {
        return oldItem == newItem
    }
}
