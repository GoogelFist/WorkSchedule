package com.github.googelfist.workshedule.di

import com.github.googelfist.workshedule.presentation.DefaultScheduleFragment
import com.github.googelfist.workshedule.presentation.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [DefaultModule::class])
@Singleton
interface ApplicationComponent {
    fun inject(fragment: DefaultScheduleFragment)

    fun inject(mainActivity: MainActivity)
}