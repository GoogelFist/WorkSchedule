package com.github.googelfist.workschedule.presentation

import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import kotlin.math.abs

class MainActivityGestureListener : GestureDetector.SimpleOnGestureListener() {

    lateinit var onSwipeUp: (() -> Unit)
    lateinit var onSwipeDown: (() -> Unit)
    lateinit var onSwipeRight: (() -> Unit)
    lateinit var onSwipeLeft: (() -> Unit)

    override fun onDown(event: MotionEvent): Boolean {
        Log.d(DEBUG_TAG, "onDown: $event")

        return false
    }

    override fun onFling(
        event1: MotionEvent,
        event2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        Log.d(DEBUG_TAG, "onFling: $event1 $event2")

        if (abs(velocityX) < VELOCITY_THRESHOLD && abs(velocityY) < VELOCITY_THRESHOLD) {
            return false
        }

        if (abs(velocityX) > abs(velocityY)) {
            if (velocityX >= 0) {
                Log.i(DEBUG_TAG, "onSwipeRight")
                onSwipeRight.invoke()
            } else {
                Log.i(DEBUG_TAG, "onSwipeLeft")
                onSwipeLeft.invoke()
            }
        } else {
            if (velocityY >= 0) {
                Log.i(DEBUG_TAG, "onSwipeDown")
                onSwipeDown.invoke()
            } else {
                Log.i(DEBUG_TAG, "onSwipeUp")
                onSwipeUp.invoke()
            }
        }
        return true
    }

    override fun onDoubleTap(event: MotionEvent): Boolean {
        Log.d(DEBUG_TAG, "onDoubleTap: $event")

        return false
    }

    companion object {
        private const val DEBUG_TAG = "Gestures"
        private const val VELOCITY_THRESHOLD = 100
    }
}
