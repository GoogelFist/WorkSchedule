//package com.github.googelfist.workshedule.presentation.recycler
//
//import android.view.View
//import android.widget.TextView
//import androidx.constraintlayout.widget.ConstraintLayout
//import androidx.core.content.ContextCompat
//import androidx.recyclerview.widget.RecyclerView
//import com.github.googelfist.workshedule.R
//
//class WorkNightViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
//
//    private val workDayTextView: TextView by lazy(LazyThreadSafetyMode.NONE) {
//        view.findViewById(R.id.text_view_work_night_item)
//    }
//    private val workNightConstraintLayout: ConstraintLayout by lazy(LazyThreadSafetyMode.NONE) {
//        view.findViewById(R.id.work_night_cl)
//    }
//
//    fun bind(day: Day) {
//        when {
//            day is WorkNight && !day.currentMonth -> setNotCurrentMonthView(day)
//            day is WorkNight && day.today -> setTodayView(day)
//            day is WorkNight && day.currentMonth -> setCurrentMonthView(day)
//            else -> throw NoSuchElementException("Unknown day type")
//        }
//    }
//
//    private fun setNotCurrentMonthView(day: Day) {
//        workNightConstraintLayout.alpha = NOT_CURRENT_MONTH_ALPHA_VALUE
//        workNightConstraintLayout.setBackgroundColor(
//            ContextCompat.getColor(
//                view.context,
//                R.color.work_night
//            )
//        )
//
//        workDayTextView.background = null
//        workDayTextView.text = day.getDayValue().toString()
//    }
//
//    private fun setTodayView(day: Day) {
//        workNightConstraintLayout.alpha = FULL_ALPHA_VALUE
//        workNightConstraintLayout.setBackgroundColor(
//            ContextCompat.getColor(
//                view.context,
//                R.color.work_night
//            )
//        )
//
//        workDayTextView.background =
//            ContextCompat.getDrawable(view.context, R.drawable.today_work_night_background)
//        workDayTextView.text = day.getDayValue().toString()
//    }
//
//    private fun setCurrentMonthView(day: Day) {
//        workNightConstraintLayout.alpha = FULL_ALPHA_VALUE
//        workNightConstraintLayout.setBackgroundColor(
//            ContextCompat.getColor(
//                view.context,
//                R.color.work_night
//            )
//        )
//
//        workDayTextView.background = null
//        workDayTextView.text = day.getDayValue().toString()
//    }
//
//    // TODO: constant object helper
//    companion object {
//        private const val FULL_ALPHA_VALUE = 1f
//        private const val NOT_CURRENT_MONTH_ALPHA_VALUE = 0.3f
//    }
//}