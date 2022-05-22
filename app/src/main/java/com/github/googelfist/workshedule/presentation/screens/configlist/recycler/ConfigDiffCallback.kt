package com.github.googelfist.workshedule.presentation.screens.configlist.recycler

import androidx.recyclerview.widget.DiffUtil
import com.github.googelfist.workshedule.domain.models.Config

class ConfigDiffCallback : DiffUtil.ItemCallback<Config>() {
    override fun areItemsTheSame(oldItem: Config, newItem: Config): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Config, newItem: Config): Boolean {
        return oldItem == newItem
    }
}