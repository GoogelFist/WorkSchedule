//package com.github.googelfist.workshedule.presentation.recycler
//
//import android.view.View
//import android.widget.TextView
//import androidx.constraintlayout.widget.ConstraintLayout
//import androidx.core.content.ContextCompat
//import androidx.recyclerview.widget.RecyclerView
//import com.github.googelfist.workshedule.R
//
//class WeekendSleepOffDayViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
//
//    private val weekendDayTextView: TextView by lazy(LazyThreadSafetyMode.NONE) {
//        view.findViewById(R.id.text_view_weekend_sleep_off_day_item)
//    }
//    private val weekendSleepOffDayConstraintLayout: ConstraintLayout by lazy(LazyThreadSafetyMode.NONE) {
//        view.findViewById(R.id.weekend_sleep_off_cl)
//    }
//
//    fun bind(day: Day) {
//
//        when {
//            day is WeekendDaySleepOff && !day.currentMonth -> setNotCurrentMonthView(day)
//            day is WeekendDaySleepOff && day.today -> setTodayView(day)
//            day is WeekendDaySleepOff && day.currentMonth -> setCurrentMonthView(day)
//            else -> throw NoSuchElementException("Unknown day type")
//        }
//    }
//
//    private fun setNotCurrentMonthView(day: Day) {
//        weekendSleepOffDayConstraintLayout.alpha = NOT_CURRENT_MONTH_ALPHA_VALUE
//        weekendSleepOffDayConstraintLayout.setBackgroundColor(
//            ContextCompat.getColor(
//                view.context,
//                R.color.weekend_sleep_off_day
//            )
//        )
//
//        weekendDayTextView.background = null
//        weekendDayTextView.text = day.getDayValue().toString()
//    }
//
//    private fun setTodayView(day: Day) {
//        weekendSleepOffDayConstraintLayout.alpha = FULL_ALPHA_VALUE
//        weekendSleepOffDayConstraintLayout.setBackgroundColor(
//            ContextCompat.getColor(
//                view.context,
//                R.color.weekend_sleep_off_day
//            )
//        )
//
//        weekendDayTextView.background =
//            ContextCompat.getDrawable(view.context, R.drawable.today_weekend_sleep_off_background)
//        weekendDayTextView.text = day.getDayValue().toString()
//    }
//
//    private fun setCurrentMonthView(day: Day) {
//        weekendSleepOffDayConstraintLayout.alpha = FULL_ALPHA_VALUE
//        weekendSleepOffDayConstraintLayout.setBackgroundColor(
//            ContextCompat.getColor(
//                view.context,
//                R.color.weekend_sleep_off_day
//            )
//        )
//
//        weekendDayTextView.background = null
//        weekendDayTextView.text = day.getDayValue().toString()
//    }
//
//    // TODO: constant object helper
//    companion object {
//        private const val FULL_ALPHA_VALUE = 1f
//        private const val NOT_CURRENT_MONTH_ALPHA_VALUE = 0.3f
//    }
//}