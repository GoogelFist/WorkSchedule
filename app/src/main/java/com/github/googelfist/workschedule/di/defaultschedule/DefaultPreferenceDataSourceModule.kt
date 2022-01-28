package com.github.googelfist.workschedule.di.defaultschedule

import android.app.Application
import com.github.googelfist.workschedule.data.preferencedatasource.PreferenceDataSource
import com.github.googelfist.workschedule.data.preferencedatasource.PreferenceDataSourceImpl
import dagger.Module
import dagger.Provides

@Module
class DefaultPreferenceDataSourceModule {

    @Provides
    @DefaultSchedule
    fun provideRepository(application: Application): PreferenceDataSource {
        return PreferenceDataSourceImpl(application)
    }
}
