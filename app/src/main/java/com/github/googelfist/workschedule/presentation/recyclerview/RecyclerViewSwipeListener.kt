package com.github.googelfist.workschedule.presentation.recyclerview

import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

open class RecyclerViewSwipeListener :
    RecyclerView.OnFlingListener() {

    override fun onFling(velocityX: Int, velocityY: Int): Boolean {

        if (abs(velocityX) < SWIPE_VELOCITY_THRESHOLD && abs(velocityY) < SWIPE_VELOCITY_THRESHOLD) {
            return false
        }

        if (abs(velocityX) > abs(velocityY)) {
            if (velocityX >= 0) {
                onSwipeRight()
            } else {
                onSwipeLeft()
            }
        } else {
            if (velocityY >= 0) {
                onSwipeDown()
            } else {
                onSwipeUp()
            }
        }
        return true
    }

    open fun onSwipeRight() {}
    open fun onSwipeLeft() {}
    open fun onSwipeUp() {}
    open fun onSwipeDown() {}
}

private const val SWIPE_VELOCITY_THRESHOLD = 1500
