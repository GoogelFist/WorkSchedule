package com.github.googelfist.workshedule.di

import com.github.googelfist.workshedule.domain.formatter.DateFormatter
import com.github.googelfist.workshedule.domain.formatter.DateFormatterImpl
import com.github.googelfist.workshedule.domain.monthgenerator.def.DefaultMonthGenerator
import com.github.googelfist.workshedule.domain.monthgenerator.def.DefaultMonthGeneratorImpl
import com.github.googelfist.workshedule.domain.monthgenerator.def.daysgenerator.DefaultDaysGenerator
import com.github.googelfist.workshedule.domain.monthgenerator.def.daysgenerator.DefaultDaysGeneratorImpl
import com.github.googelfist.workshedule.domain.monthgenerator.def.fabric.DefaultDaysFabric
import com.github.googelfist.workshedule.domain.monthgenerator.def.fabric.DefaultDaysFabricImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DefaultModule {

    @Binds
    fun bindDaysFabric(impl: DefaultDaysFabricImpl): DefaultDaysFabric

    @Binds
    fun bindDaysGenerator(impl: DefaultDaysGeneratorImpl): DefaultDaysGenerator

    @Binds
    fun bindDateFormatter(impl: DateFormatterImpl): DateFormatter

    @Binds
    @Singleton
    fun bindMonthGenerator(impl: DefaultMonthGeneratorImpl): DefaultMonthGenerator
}