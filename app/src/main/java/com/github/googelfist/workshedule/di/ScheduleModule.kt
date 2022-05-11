package com.github.googelfist.workshedule.di

import com.github.googelfist.workshedule.data.RepositoryImp
import com.github.googelfist.workshedule.data.datasource.LocalDataSource
import com.github.googelfist.workshedule.data.datasource.local.RoomDataSourceImpl
import com.github.googelfist.workshedule.domain.Repository
import com.github.googelfist.workshedule.domain.formatter.DateFormatter
import com.github.googelfist.workshedule.domain.formatter.DateFormatterImpl
import com.github.googelfist.workshedule.domain.monthgenerator.ScheduleGenerator
import com.github.googelfist.workshedule.domain.monthgenerator.ScheduleGeneratorImpl
import com.github.googelfist.workshedule.domain.monthgenerator.daygenerator.DaysGenerator
import com.github.googelfist.workshedule.domain.monthgenerator.daygenerator.DaysGeneratorImpl
import com.github.googelfist.workshedule.domain.monthgenerator.fabric.DaysFabric
import com.github.googelfist.workshedule.domain.monthgenerator.fabric.DaysFabricImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface ScheduleModule {

    @Binds
    @Singleton
    fun bindDaysFabric(impl: DaysFabricImpl): DaysFabric

    @Binds
    fun bindDaysGenerator(impl: DaysGeneratorImpl): DaysGenerator

    @Binds
    fun bindDateFormatter(impl: DateFormatterImpl): DateFormatter

    @Binds
    @Singleton
    fun bindMonthGenerator(impl: ScheduleGeneratorImpl): ScheduleGenerator

    @Binds
    @Singleton
    fun bindRepository(impl: RepositoryImp): Repository

    @Binds
    fun bindDataSource(impl: RoomDataSourceImpl): LocalDataSource
}