//package com.github.googelfist.workshedule.presentation.recycler
//
//import android.graphics.Color
//import android.view.View
//import android.widget.TextView
//import androidx.constraintlayout.widget.ConstraintLayout
//import androidx.core.content.ContextCompat
//import androidx.recyclerview.widget.RecyclerView
//import com.github.googelfist.workshedule.R
//
//class WorkDayViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
//
//    private val workDayTextView: TextView by lazy(LazyThreadSafetyMode.NONE) {
//        view.findViewById(R.id.text_view_work_day_item)
//    }
//    private val workDayConstraintLayout: ConstraintLayout by lazy(LazyThreadSafetyMode.NONE) {
//        view.findViewById(R.id.work_day_cl)
//    }
//
//    fun bind(day: Day) {
//        when {
//            day is WorkDay && !day.currentMonth -> setNotCurrentMonthView(day)
//            day is WorkDay && day.today -> setTodayView(day)
//            day is WorkDay && day.currentMonth -> setCurrentMonthView(day)
//            else -> throw NoSuchElementException("Unknown day type")
//        }
//    }
//
//    private fun setNotCurrentMonthView(day: Day) {
//        workDayConstraintLayout.alpha = NOT_CURRENT_MONTH_ALPHA_VALUE
////        workDayConstraintLayout.setBackgroundColor(
////            ContextCompat.getColor(
////                view.context,
////                R.color.work_day
////            )
////        )
//
//        workDayConstraintLayout.setBackgroundColor(Color.parseColor((day as WorkDay).backgroundColor))
//
//
//        workDayTextView.background = null
//        workDayTextView.text = day.getDayValue().toString()
//    }
//
//    private fun setTodayView(day: Day) {
//        workDayConstraintLayout.alpha = FULL_ALPHA_VALUE
////        workDayConstraintLayout.setBackgroundColor(
////            ContextCompat.getColor(
////                view.context,
////                R.color.work_day
////            )
////        )
//        workDayConstraintLayout.setBackgroundColor(Color.parseColor((day as WorkDay).backgroundColor))
//
//        workDayTextView.background =
//            ContextCompat.getDrawable(view.context, R.drawable.today_work_background)
//        workDayTextView.text = day.getDayValue().toString()
//    }
//
//    private fun setCurrentMonthView(day: Day) {
//        workDayConstraintLayout.alpha = FULL_ALPHA_VALUE
////        workDayConstraintLayout.setBackgroundColor(
////            ContextCompat.getColor(
////                view.context,
////                R.color.work_day
////            )
////        )
//        workDayConstraintLayout.setBackgroundColor(Color.parseColor((day as WorkDay).backgroundColor))
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