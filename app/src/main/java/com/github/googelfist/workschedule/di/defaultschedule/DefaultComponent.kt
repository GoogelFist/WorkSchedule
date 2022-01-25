package com.github.googelfist.workschedule.di.defaultschedule

import com.github.googelfist.workschedule.presentation.fragments.DefaultScheduleFragment
import dagger.Component
import javax.inject.Scope

@Component(modules = [DefaultModule::class])
@DefaultSchedule
interface DefaultComponent {

    fun inject(fragment: DefaultScheduleFragment)
}

@Scope
annotation class DefaultSchedule
