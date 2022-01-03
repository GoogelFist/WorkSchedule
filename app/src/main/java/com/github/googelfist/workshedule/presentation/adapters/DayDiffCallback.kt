package com.github.googelfist.workshedule.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.github.googelfist.workshedule.domain.models.Day

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