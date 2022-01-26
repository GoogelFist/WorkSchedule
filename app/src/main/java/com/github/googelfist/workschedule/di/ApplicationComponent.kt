package com.github.googelfist.workschedule.di

import android.app.Application
import com.github.googelfist.workschedule.presentation.ScheduleActivity
import dagger.BindsInstance
import dagger.Component

@Component(modules = [PreferenceDataSourceModule::class, ApplicationModule::class])
@ApplicationScope
interface ApplicationComponent {

    fun inject(activity: ScheduleActivity)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(application: Application): Builder

        fun build(): ApplicationComponent
    }
}

annotation class ApplicationScope
