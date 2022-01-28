package com.github.googelfist.workschedule.di.defaultschedule

import com.github.googelfist.workschedule.data.DefaultRepositoryImpl
import com.github.googelfist.workschedule.data.scheduledatasource.ScheduleGenerator
import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.DefaultScheduleGeneratorImp
import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.datecontainer.DateContainer
import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.datecontainer.DateContainerImpl
import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.daysgenerator.DaysGenerator
import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.daysgenerator.DaysGeneratorImpl
import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.fabric.DaysFabric
import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.fabric.DefaultDaysFabricImpl
import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.formatter.DateFormatter
import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.formatter.DateFormatterImpl
import com.github.googelfist.workschedule.domain.ScheduleRepository
import dagger.Binds
import dagger.Module

@Module
interface DefaultModule {

    @Binds
    fun bindRepository(impl: DefaultRepositoryImpl): ScheduleRepository

    @Binds
    @DefaultSchedule
    fun bindScheduleGenerator(impl: DefaultScheduleGeneratorImp): ScheduleGenerator

    @Binds
    fun bindDayGenerator(impl: DaysGeneratorImpl): DaysGenerator

    @Binds
    fun bindFormatter(impl: DateFormatterImpl): DateFormatter

    @Binds
    fun bindDaysFabric(impl: DefaultDaysFabricImpl): DaysFabric

    @Binds
    fun bindDateContainer(impl: DateContainerImpl): DateContainer
}
