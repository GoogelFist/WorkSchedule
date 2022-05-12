package com.github.googelfist.workshedule.presentation.config.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.ListAdapter
import com.github.googelfist.workshedule.R
import com.github.googelfist.workshedule.domain.models.DayType

class DayTypeListAdapter : ListAdapter<DayType, DayTypeViewHolder>(DayTypeDiffCallback()) {
    lateinit var onEditColorButtonClickListener: (button: ImageButton, position: Int) -> Unit
    lateinit var onEditTitleButtonClickListener: (button: ImageButton, position: Int) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayTypeViewHolder {

        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.day_type_edit_item, parent, false)

        return DayTypeViewHolder(layout)
    }

    override fun onBindViewHolder(holder: DayTypeViewHolder, position: Int) {
        val dayType = getItem(position)

        val editColorButton = holder.editColorButton
        onEditColorButtonClickListener(editColorButton, position)

        val editTitleButton = holder.editTitleButton
        onEditTitleButtonClickListener(editTitleButton, position)

        holder.bind(dayType)
    }
}