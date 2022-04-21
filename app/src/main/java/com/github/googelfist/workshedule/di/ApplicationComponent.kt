package com.github.googelfist.workshedule.di

import android.app.Application
import com.github.googelfist.workshedule.presentation.MainActivity
import com.github.googelfist.workshedule.presentation.def.DefaultScheduleFragment
import com.github.googelfist.workshedule.presentation.schedule.ScheduleFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [DefaultModule::class, TwoInTwoModule::class, RoomModule::class])
@Singleton
interface ApplicationComponent {
    fun inject(fragment: DefaultScheduleFragment)
    fun inject(fragment: ScheduleFragment)

    fun inject(mainActivity: MainActivity)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(application: Application): Builder

        fun build(): ApplicationComponent
    }
}