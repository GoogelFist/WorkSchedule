package com.github.googelfist.workschedule.presentation.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.github.googelfist.workschedule.presentation.fragments.DefaultScheduleFragment

class DefaultSchedulePagerAdapter(fragment: DefaultSchedulePagerFragment) :
    FragmentStateAdapter(fragment) {
    private val fragmentList = mutableListOf<DefaultScheduleFragment>()

    init {
        fragmentList.add(DefaultScheduleFragment.newInstance())
        fragmentList.add(DefaultScheduleFragment.newInstance())
        fragmentList.add(DefaultScheduleFragment.newInstance())
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}
