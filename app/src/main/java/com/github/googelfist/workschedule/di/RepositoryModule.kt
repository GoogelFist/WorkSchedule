package com.github.googelfist.workschedule.di

import android.app.Application
import com.github.googelfist.workschedule.data.repository.PreferenceRepositoryImpl
import com.github.googelfist.workschedule.domain.PreferenceRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    @ApplicationScope
    fun provideRepository(application: Application): PreferenceRepository {
        return PreferenceRepositoryImpl(application)
    }
}
