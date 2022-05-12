package com.github.googelfist.workshedule.presentation.config.recycler

import android.graphics.Color
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.github.googelfist.workshedule.R
import com.github.googelfist.workshedule.domain.models.DayType

class DayTypeViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    val editColorButton: Button by lazy(LazyThreadSafetyMode.NONE) {
        view.findViewById(R.id.button_edit_color)
    }
    val editTitleButton: Button by lazy(LazyThreadSafetyMode.NONE) {
        view.findViewById(R.id.button_edit_title)
    }

    private val constraintLayout: ConstraintLayout by lazy(LazyThreadSafetyMode.NONE) {
        view.findViewById(R.id.day_item_config_cl)
    }
    private val dayTextViewTitle: TextView by lazy(LazyThreadSafetyMode.NONE) {
        view.findViewById(R.id.tv_day_item_config_title)
    }
    private val colorExample: View by lazy(LazyThreadSafetyMode.NONE) {
        view.findViewById(R.id.color_example)
    }
    private val titleExampleTextView: TextView by lazy(LazyThreadSafetyMode.NONE) {
        view.findViewById(R.id.tv_title_example)
    }

    fun bind(dayType: DayType) {
        constraintLayout.setBackgroundColor(Color.parseColor(dayType.backgroundColor))

        dayTextViewTitle.text = dayType.title

        colorExample.setBackgroundColor(Color.parseColor(dayType.backgroundColor))
        titleExampleTextView.text = dayType.title
    }
}