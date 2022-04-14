package com.github.googelfist.workshedule.di

import com.github.googelfist.workshedule.presentation.DefaultScheduleFragment
import dagger.Component

@Component(modules = [DefaultModule::class])
interface ApplicationComponent {
    fun inject(fragment: DefaultScheduleFragment)
}