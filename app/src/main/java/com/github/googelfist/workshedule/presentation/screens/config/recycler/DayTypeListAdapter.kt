package com.github.googelfist.workshedule.presentation.screens.config.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.github.googelfist.workshedule.R
import com.github.googelfist.workshedule.domain.models.DayType

class DayTypeListAdapter : ListAdapter<DayType, DayTypeViewHolder>(DayTypeDiffCallback()) {
    lateinit var onEditColorButtonClickListener: (position: Int) -> Unit
    lateinit var onEditTitleButtonClickListener: (position: Int) -> Unit
    lateinit var onDeleteButtonClickListener: (position: Int) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayTypeViewHolder {

        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.day_type_edit_item, parent, false)

        val viewHolder = DayTypeViewHolder(layout)

        val editColorButton = viewHolder.editColorButton
        editColorButton.setOnClickListener {
            val position = viewHolder.adapterPosition
            if (position != NO_POSITION) {
                onEditColorButtonClickListener.invoke(position)
            }
        }

        val editTitleButton = viewHolder.editTitleButton
        editTitleButton.setOnClickListener {
            val position = viewHolder.adapterPosition
            if (position != NO_POSITION) {
                onEditTitleButtonClickListener.invoke(position)
            }
        }

        val deleteButton = viewHolder.deleteButton
        deleteButton.setOnClickListener {
            val position = viewHolder.adapterPosition
            if (position != NO_POSITION) {
                onDeleteButtonClickListener.invoke(position)
            }
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: DayTypeViewHolder, position: Int) {
        val dayType = getItem(position)

        holder.bind(dayType)
    }

    companion object {
        private const val NO_POSITION = -1
    }
}