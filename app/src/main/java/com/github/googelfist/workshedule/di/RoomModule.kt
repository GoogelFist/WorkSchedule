package com.github.googelfist.workshedule.di

import android.app.Application
import androidx.room.Room
import com.github.googelfist.workshedule.data.datasource.local.ParametersDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideDataRoomDatabase(application: Application): ParametersDataBase {
        return Room.databaseBuilder(application, ParametersDataBase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries() // TODO: add coroutines
            .build()
    }

    @Singleton
    @Provides
    fun providesMovieDao(parametersDataBase: ParametersDataBase) =
        parametersDataBase.getParametersDao()

    companion object {
        private const val DB_NAME = "parameters_database"
    }
}