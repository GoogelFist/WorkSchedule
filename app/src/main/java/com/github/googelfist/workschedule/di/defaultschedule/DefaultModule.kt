package com.github.googelfist.workschedule.di.defaultschedule

import com.github.googelfist.workschedule.data.schedulegenerator.DefaultScheduleGeneratorImp
import com.github.googelfist.workschedule.data.schedulegenerator.daysgenerator.DaysGenerator
import com.github.googelfist.workschedule.data.schedulegenerator.daysgenerator.DaysGeneratorImpl
import com.github.googelfist.workschedule.data.schedulegenerator.fabric.DaysFabric
import com.github.googelfist.workschedule.data.schedulegenerator.fabric.DefaultDaysFabricImpl
import com.github.googelfist.workschedule.data.schedulegenerator.formatter.DateFormatter
import com.github.googelfist.workschedule.data.schedulegenerator.formatter.DateFormatterImpl
import com.github.googelfist.workschedule.domain.ScheduleGenerator
import dagger.Binds
import dagger.Module

@Module
interface DefaultModule {

    @Binds
    @DefaultSchedule
    fun bindScheduleGenerator(impl: DefaultScheduleGeneratorImp): ScheduleGenerator

    @Binds
    fun bindDayGenerator(impl: DaysGeneratorImpl): DaysGenerator

    @Binds
    fun bindFormatter(impl: DateFormatterImpl): DateFormatter

    @Binds
    fun bindDaysFabric(impl: DefaultDaysFabricImpl): DaysFabric
}
