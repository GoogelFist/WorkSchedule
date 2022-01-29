package com.github.googelfist.workschedule.presentation.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.github.googelfist.workschedule.domain.schedulegenerator.models.Day

class DayDiffCallback : DiffUtil.ItemCallback<Day>() {
    override fun areItemsTheSame(oldItem: Day, newItem: Day): Boolean {
        return oldItem.day == newItem.day && oldItem.month == newItem.month && oldItem.year == newItem.year
    }

    override fun areContentsTheSame(oldItem: Day, newItem: Day): Boolean {
        return oldItem == newItem
    }
}
