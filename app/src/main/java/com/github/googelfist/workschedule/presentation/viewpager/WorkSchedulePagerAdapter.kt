package com.github.googelfist.workschedule.presentation.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.github.googelfist.workschedule.presentation.fragments.WorkScheduleFragment

class WorkSchedulePagerAdapter(fragment: WorkSchedulePagerFragment) :
    FragmentStateAdapter(fragment) {
    private val fragmentList = mutableListOf<WorkScheduleFragment>()

    init {
        fragmentList.add(WorkScheduleFragment.newTwoInTwoPreviousMonthInstance())
        fragmentList.add(WorkScheduleFragment.newTwoInTwoCurrentMonthInstance())
        fragmentList.add(WorkScheduleFragment.newTwoInTwoNextMonthInstance())
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    fun setPreviousFragmentToStart() {
        fragmentList[ZERO_VALUE] = WorkScheduleFragment.newTwoInTwoPreviousMonthInstance()
    }

    fun setNextFragmentToEnd() {
        fragmentList[TWO_VALUE] = WorkScheduleFragment.newTwoInTwoNextMonthInstance()
    }

    companion object {
        private const val ZERO_VALUE = 0
        private const val TWO_VALUE = 2
    }
}
