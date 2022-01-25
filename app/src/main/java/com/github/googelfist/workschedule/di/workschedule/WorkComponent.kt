package com.github.googelfist.workschedule.di.workschedule

import android.app.Application
import com.github.googelfist.workschedule.presentation.fragments.WorkScheduleFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope

@Component(modules = [WorkRepositoryModule::class, TwoInTwoModule::class])
@WorkSchedule
interface WorkComponent {

    fun inject(fragment: WorkScheduleFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(application: Application): Builder

        fun build(): WorkComponent
    }
}

@Scope
annotation class WorkSchedule
