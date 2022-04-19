package com.github.googelfist.workshedule.di

import com.github.googelfist.workshedule.presentation.MainActivity
import com.github.googelfist.workshedule.presentation.def.DefaultScheduleFragment
import com.github.googelfist.workshedule.presentation.schedule.ScheduleFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [DefaultModule::class, TwoInTwoModule::class])
@Singleton
interface ApplicationComponent {
    fun inject(fragment: DefaultScheduleFragment)
    fun inject(fragment: ScheduleFragment)

    fun inject(mainActivity: MainActivity)
}