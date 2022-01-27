package com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.models

open class Day(
    open val day: Int,
    open val month: Int,
    open val year: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Day

        if (day != other.day) return false
        if (month != other.month) return false
        if (year != other.year) return false

        return true
    }

    override fun hashCode(): Int {
        var result = day
        result = 31 * result + month
        result = 31 * result + year
        return result
    }
}
