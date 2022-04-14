package com.github.googelfist.workshedule.di

import com.github.googelfist.workshedule.domain.DefaultMonthGeneratorImpl
import com.github.googelfist.workshedule.domain.MonthGenerator
import com.github.googelfist.workshedule.domain.daysgenerator.DaysFabric
import com.github.googelfist.workshedule.domain.daysgenerator.DaysGenerator
import com.github.googelfist.workshedule.domain.daysgenerator.DefaultDaysFabricImpl
import com.github.googelfist.workshedule.domain.daysgenerator.DefaultDaysGeneratorImpl
import com.github.googelfist.workshedule.domain.formatter.DateFormatter
import com.github.googelfist.workshedule.domain.formatter.DateFormatterImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DefaultModule {

    @Binds
    fun bindDaysFabric(impl: DefaultDaysFabricImpl): DaysFabric

    @Binds
    fun bindDaysGenerator(impl: DefaultDaysGeneratorImpl): DaysGenerator

    @Binds
    fun bindDateFormatter(impl: DateFormatterImpl): DateFormatter

    @Binds
    @Singleton
    fun bindMonthGenerator(impl: DefaultMonthGeneratorImpl): MonthGenerator
}