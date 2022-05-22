package com.github.googelfist.workshedule.presentation.screens.configlist.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.github.googelfist.workshedule.R
import com.github.googelfist.workshedule.domain.models.Config

class ConfigListAdapter : ListAdapter<Config, ConfigViewHolder>(ConfigDiffCallback()) {
    lateinit var onEditConfigButtonClickListener: (position: Int) -> Unit
    lateinit var onRemoveConfigButtonClickListener: (position: Int) -> Unit
    lateinit var onChooseCurrentPatternClickListener: (position: Int) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConfigViewHolder {

        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.config_item, parent, false)

        val viewHolder = ConfigViewHolder(layout)

        val editPatternButton = viewHolder.editPatternButton
        editPatternButton.setOnClickListener {
            val position = viewHolder.adapterPosition
            if (position != NO_POSITION) {
                onEditConfigButtonClickListener.invoke(position)
            }
        }

        val removeConfigButton = viewHolder.removePatternButton
        removeConfigButton.setOnClickListener {
            val position = viewHolder.adapterPosition
            if (position != NO_POSITION) {
                onRemoveConfigButtonClickListener.invoke(position)
            }
        }

        val patternName = viewHolder.patternTextViewTitle
        patternName.setOnClickListener {
            val position = viewHolder.adapterPosition
            if (position != NO_POSITION) {
                onChooseCurrentPatternClickListener.invoke(position)
            }
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ConfigViewHolder, position: Int) {
        val pattern = getItem(position)

        holder.bind(pattern)
    }

    companion object {
        private const val NO_POSITION = -1
    }
}