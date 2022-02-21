package com.github.googelfist.workschedule.di.workschedule

import android.app.Application
import com.github.googelfist.workschedule.presentation.fragments.WorkScheduleFragment
import com.github.googelfist.workschedule.presentation.viewpager.WorkSchedulePagerFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope

@Component(modules = [WorkPreferenceDataSourceModule::class, TwoInTwoModule::class])
@WorkSchedule
interface WorkComponent {

    fun inject(fragment: WorkScheduleFragment)
    fun inject(fragment: WorkSchedulePagerFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(application: Application): Builder

        fun build(): WorkComponent
    }
}

@Scope
annotation class WorkSchedule
