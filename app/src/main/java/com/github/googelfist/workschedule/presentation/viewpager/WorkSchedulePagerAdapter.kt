package com.github.googelfist.workschedule.presentation.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.github.googelfist.workschedule.presentation.fragments.WorkScheduleFragment

class WorkSchedulePagerAdapter(fragment: WorkSchedulePagerFragment) :
    FragmentStateAdapter(fragment) {
    private val fragmentList = mutableListOf<WorkScheduleFragment>()

    init {
        fragmentList.add(WorkScheduleFragment.newTwoInTwoFragment())
        fragmentList.add(WorkScheduleFragment.newTwoInTwoFragment())
        fragmentList.add(WorkScheduleFragment.newTwoInTwoFragment())
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}
