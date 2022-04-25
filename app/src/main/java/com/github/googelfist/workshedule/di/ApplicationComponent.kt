package com.github.googelfist.workshedule.di

import android.app.Application
import com.github.googelfist.workshedule.presentation.MainActivity
import com.github.googelfist.workshedule.presentation.schedule.BottomSheetFragment
import com.github.googelfist.workshedule.presentation.schedule.ScheduleFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ScheduleModule::class, RoomModule::class])
@Singleton
interface ApplicationComponent {
    fun inject(fragment: ScheduleFragment)

    fun inject(mainActivity: MainActivity)

    fun inject(bottomSheetFragment: BottomSheetFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(application: Application): Builder

        fun build(): ApplicationComponent
    }
}