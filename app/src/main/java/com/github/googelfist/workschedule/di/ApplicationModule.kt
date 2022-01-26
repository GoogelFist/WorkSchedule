package com.github.googelfist.workschedule.di

import com.github.googelfist.workschedule.data.PreferenceRepositoryImpl
import com.github.googelfist.workschedule.domain.PreferenceRepository
import dagger.Binds
import dagger.Module

@Module
interface ApplicationModule {

    @Binds
    fun bindPreferenceRepository(impl: PreferenceRepositoryImpl): PreferenceRepository
}
