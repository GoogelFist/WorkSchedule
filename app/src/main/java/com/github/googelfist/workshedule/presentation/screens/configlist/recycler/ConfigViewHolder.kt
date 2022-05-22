package com.github.googelfist.workshedule.presentation.screens.configlist.recycler

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.github.googelfist.workshedule.R
import com.github.googelfist.workshedule.domain.models.Config

class ConfigViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    val editPatternButton: ImageButton by lazy(LazyThreadSafetyMode.NONE) {
        view.findViewById(R.id.button_edit_config)
    }

    val removePatternButton: ImageButton by lazy(LazyThreadSafetyMode.NONE) {
        view.findViewById(R.id.button_remove_config)
    }

    val patternTextViewTitle: TextView by lazy(LazyThreadSafetyMode.NONE) {
        view.findViewById(R.id.tv_config_name)
    }


    fun bind(config: Config) {

        patternTextViewTitle.text = config.configName

        if (config.isCurrent) {
            patternTextViewTitle.background =
                ContextCompat.getDrawable(view.context, R.drawable.config_choosed_background)
        } else {
            patternTextViewTitle.background =
                ContextCompat.getDrawable(view.context, R.drawable.config_text_view_background)
        }
    }
}