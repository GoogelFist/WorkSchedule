package com.github.googelfist.workshedule.domain.models.day

data class WeekendDay(
    val day: Int,
    val month: Int,
    val year: Int,
    val today: Boolean,
    val currentMonth: Boolean
) : Day, Today, CurrentMonth {
    override fun getDayValue(): Int {
        return day
    }

    override fun getMonthValue(): Int {
        return month
    }

    override fun getYearValue(): Int {
        return year
    }

    override fun isToday(): Boolean {
        return today
    }

    override fun isCurrentMonth(): Boolean {
        return currentMonth
    }
}
