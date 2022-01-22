package com.github.googelfist.workschedule.domain.models

open class Day(
    open val value: Int,
    open val month: Int,
    open val year: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Day

        if (value != other.value) return false
        if (month != other.month) return false
        if (year != other.year) return false

        return true
    }

    override fun hashCode(): Int {
        var result = value
        result = 31 * result + month
        result = 31 * result + year
        return result
    }
}
