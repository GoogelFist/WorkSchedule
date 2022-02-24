package com.github.googelfist.workschedule.presentation.viewpager

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs
import kotlin.math.max

class ZoomOutPageTransformer : ViewPager2.PageTransformer {

    override fun transformPage(view: View, position: Float) {
        view.apply {
            val pageWidth = width
            val pageHeight = height
            when {
                position < MINUS_ONE_VALUE -> {
                    alpha = ZERO_VALUE.toFloat()
                }
                position <= ONE_VALUE -> {
                    val scaleFactor = max(MIN_SCALE, ONE_VALUE - abs(position))
                    val vertMargin = pageHeight * (ONE_VALUE - scaleFactor) / TWO_VALUE
                    val horzMargin = pageWidth * (ONE_VALUE - scaleFactor) / TWO_VALUE
                    translationX = if (position < ZERO_VALUE) {
                        horzMargin - vertMargin / TWO_VALUE
                    } else {
                        horzMargin + vertMargin / TWO_VALUE
                    }

                    scaleX = scaleFactor
                    scaleY = scaleFactor

                    alpha =
                        (MIN_ALPHA + (((scaleFactor - MIN_SCALE) / (ONE_VALUE - MIN_SCALE)) * (ONE_VALUE - MIN_ALPHA)))
                }
                else -> {
                    alpha = ZERO_VALUE.toFloat()
                }
            }
        }
    }

    companion object {
        private const val MIN_SCALE = 0.85f
        private const val MIN_ALPHA = 0.5f

        private const val ZERO_VALUE = 0
        private const val ONE_VALUE = 1
        private const val TWO_VALUE = 2
        private const val MINUS_ONE_VALUE = -1
    }
}
