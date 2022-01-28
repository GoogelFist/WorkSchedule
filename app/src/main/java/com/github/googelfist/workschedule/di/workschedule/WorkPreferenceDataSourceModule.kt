package com.github.googelfist.workschedule.di.workschedule

import android.app.Application
import com.github.googelfist.workschedule.data.preferencedatasource.PreferenceDataSource
import com.github.googelfist.workschedule.data.preferencedatasource.PreferenceDataSourceImpl
import dagger.Module
import dagger.Provides

@Module
class WorkPreferenceDataSourceModule {

    @Provides
    @WorkSchedule
    fun providePreferenceDataSource(application: Application): PreferenceDataSource {
        return PreferenceDataSourceImpl(application)
    }
}
