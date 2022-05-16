package com.github.googelfist.workshedule.presentation.config.recycler

import androidx.recyclerview.widget.DiffUtil
import com.github.googelfist.workshedule.domain.models.DayType

class DayTypeDiffCallback : DiffUtil.ItemCallback<DayType>() {

    override fun areItemsTheSame(oldItem: DayType, newItem: DayType): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DayType, newItem: DayType): Boolean {
        return oldItem == newItem
    }
}