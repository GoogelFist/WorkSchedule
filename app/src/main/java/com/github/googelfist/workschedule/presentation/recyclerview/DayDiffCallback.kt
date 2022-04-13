package com.github.googelfist.workschedule.presentation.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.github.googelfist.workschedule.domain.schedulegenerator.models.Day

class DayDiffCallback : DiffUtil.ItemCallback<Day>() {
    override fun areItemsTheSame(oldItem: Day, newItem: Day): Boolean {
        return oldItem.getDayValue() == newItem.getDayValue() && oldItem.getMonthValue() == newItem.getMonthValue() && oldItem.getYearValue() == newItem.getYearValue()
    }

    override fun areContentsTheSame(oldItem: Day, newItem: Day): Boolean {
        return oldItem.equals(newItem)
    }
}
