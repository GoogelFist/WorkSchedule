package com.github.googelfist.workshedule.di

import com.github.googelfist.workshedule.data.RepositoryImp
import com.github.googelfist.workshedule.data.datasource.LocalDataSource
import com.github.googelfist.workshedule.data.datasource.local.RoomDataSourceImpl
import com.github.googelfist.workshedule.domain.Repository
import com.github.googelfist.workshedule.domain.monthgenerator.twointwo.WorkTwoInTwoMonthGenerator
import com.github.googelfist.workshedule.domain.monthgenerator.twointwo.WorkTwoInTwoMonthGeneratorImpl
import com.github.googelfist.workshedule.domain.monthgenerator.twointwo.daygenerator.WorkTwoInTwoDaysGenerator
import com.github.googelfist.workshedule.domain.monthgenerator.twointwo.daygenerator.WorkTwoInTwoDaysGeneratorImpl
import com.github.googelfist.workshedule.domain.monthgenerator.twointwo.fabric.WorkTwoInTwoDaysFabric
import com.github.googelfist.workshedule.domain.monthgenerator.twointwo.fabric.WorkTwoInTwoDaysFabricImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface TwoInTwoModule {

    @Binds
    fun bindDaysFabric(impl: WorkTwoInTwoDaysFabricImpl): WorkTwoInTwoDaysFabric

    @Binds
    fun bindDaysGenerator(impl: WorkTwoInTwoDaysGeneratorImpl): WorkTwoInTwoDaysGenerator

//    @Binds
//    fun bindDateFormatter(impl: DateFormatterImpl): DateFormatter

    @Binds
    @Singleton
    fun bindMonthGenerator(impl: WorkTwoInTwoMonthGeneratorImpl): WorkTwoInTwoMonthGenerator

    @Binds
    @Singleton
    fun bindRepository(impl: RepositoryImp): Repository

    @Binds
    fun bindDataSource(impl: RoomDataSourceImpl): LocalDataSource
}