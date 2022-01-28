package com.github.googelfist.workschedule.di.defaultschedule

import android.app.Application
import com.github.googelfist.workschedule.presentation.fragments.DefaultScheduleFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope

@Component(modules = [DefaultModule::class, DefaultPreferenceDataSourceModule::class])
@DefaultSchedule
interface DefaultComponent {

    fun inject(fragment: DefaultScheduleFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(application: Application): Builder

        fun build(): DefaultComponent
    }
}

@Scope
annotation class DefaultSchedule
