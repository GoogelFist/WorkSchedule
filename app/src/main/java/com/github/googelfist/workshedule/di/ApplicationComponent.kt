package com.github.googelfist.workshedule.di

import android.app.Application
import com.github.googelfist.workshedule.presentation.MainActivity
import com.github.googelfist.workshedule.presentation.screens.config.ScheduleConfigFragment
import com.github.googelfist.workshedule.presentation.screens.configlist.ConfigsListFragment
import com.github.googelfist.workshedule.presentation.screens.schedule.ScheduleFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ScheduleModule::class, RoomModule::class, CoroutineDispatcherModule::class])
@Singleton
interface ApplicationComponent {
    fun inject(fragment: ScheduleFragment)

    fun inject(mainActivity: MainActivity)

    fun inject(scheduleConfigFragment: ScheduleConfigFragment)

    fun inject(configsListFragment: ConfigsListFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(application: Application): Builder

        fun build(): ApplicationComponent
    }
}