package com.github.googelfist.workshedule.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.googelfist.workshedule.R
import com.github.googelfist.workshedule.domain.Day

class DayListAdapter : RecyclerView.Adapter<DayViewHolder>() {

    var dayList = listOf<Day>()
        set(value) {
            field = value
            notifyDataSetChanged()
            // TODO: 26-Dec-21 replace this notify
        }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.calendar_cell,
            parent,
            false
        )
        return DayViewHolder(view)
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        val day = dayList[position]
        holder.day.text = day.value.toString()
    }

    override fun getItemCount(): Int {
        return dayList.size
    }
}