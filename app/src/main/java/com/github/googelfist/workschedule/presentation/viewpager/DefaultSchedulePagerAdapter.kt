package com.github.googelfist.workschedule.presentation.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.github.googelfist.workschedule.presentation.fragments.DefaultScheduleFragment

class DefaultSchedulePagerAdapter(fragment: DefaultSchedulePagerFragment) :
    FragmentStateAdapter(fragment) {
    private val fragmentList = mutableListOf<DefaultScheduleFragment>()

    init {
        fragmentList.add(DefaultScheduleFragment.newPreviousMonthInstance())
        fragmentList.add(DefaultScheduleFragment.newCurrentMonthInstance())
        fragmentList.add(DefaultScheduleFragment.newNextMonthInstance())
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    fun setPreviousFragmentToStart() {
        fragmentList[ZERO_VALUE] = DefaultScheduleFragment.newPreviousMonthInstance()
    }

    fun setNextFragmentToEnd() {
        fragmentList[TWO_VALUE] = DefaultScheduleFragment.newNextMonthInstance()
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    companion object {
        private const val ZERO_VALUE = 0
        private const val TWO_VALUE = 2
    }
}
