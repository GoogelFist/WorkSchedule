package com.github.googelfist.workschedule.di

import android.app.Application
import com.github.googelfist.workschedule.data.preferencedatasource.PreferenceDataSource
import com.github.googelfist.workschedule.data.preferencedatasource.PreferenceDataSourceImpl
import dagger.Module
import dagger.Provides

@Module
class PreferenceDataSourceModule {

    @Provides
    @ApplicationScope
    fun providePreferenceDataSource(application: Application): PreferenceDataSource {
        return PreferenceDataSourceImpl(application)
    }
}
