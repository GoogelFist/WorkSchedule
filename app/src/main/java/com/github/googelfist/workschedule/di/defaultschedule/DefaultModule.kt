package com.github.googelfist.workschedule.di.defaultschedule

import com.github.googelfist.workschedule.domain.ScheduleGenerator
import com.github.googelfist.workschedule.domain.schedulegenerator.DefaultScheduleGeneratorImp
import com.github.googelfist.workschedule.domain.schedulegenerator.datecontainer.DateContainer
import com.github.googelfist.workschedule.domain.schedulegenerator.datecontainer.DateContainerImpl
import com.github.googelfist.workschedule.domain.schedulegenerator.daysgenerator.DaysGenerator
import com.github.googelfist.workschedule.domain.schedulegenerator.daysgenerator.DaysGeneratorImpl
import com.github.googelfist.workschedule.domain.schedulegenerator.daysgenerator.fabric.DaysFabric
import com.github.googelfist.workschedule.domain.schedulegenerator.daysgenerator.fabric.DefaultDaysFabricImpl
import com.github.googelfist.workschedule.domain.schedulegenerator.formatter.DateFormatter
import com.github.googelfist.workschedule.domain.schedulegenerator.formatter.DateFormatterImpl
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

    @Binds
    fun bindDateContainer(impl: DateContainerImpl): DateContainer
}
