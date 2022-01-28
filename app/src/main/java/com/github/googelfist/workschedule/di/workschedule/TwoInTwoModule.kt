package com.github.googelfist.workschedule.di.workschedule

import com.github.googelfist.workschedule.data.WorkRepositoryImpl
import com.github.googelfist.workschedule.data.scheduledatasource.ScheduleGenerator
import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.WorkScheduleGeneratorImpl
import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.datecontainer.DateContainer
import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.datecontainer.DateContainerImpl
import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.daysgenerator.DaysGenerator
import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.daysgenerator.DaysGeneratorImpl
import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.fabric.DaysFabric
import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.fabric.WorkDaysFabric
import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.fabric.WorkDaysFabricAdapter
import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.fabric.WorkDaysFabricImpl
import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.formatter.DateFormatter
import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.formatter.DateFormatterImpl
import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.schedulesetup.ScheduleSetup
import com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.schedulesetup.TwoInTwoWorkScheduleSetup
import com.github.googelfist.workschedule.domain.ScheduleRepository
import dagger.Binds
import dagger.Module

@Module()
interface TwoInTwoModule {

    @Binds
    fun bindRepository(impl: WorkRepositoryImpl): ScheduleRepository

    @Binds
    @WorkSchedule
    fun bindScheduleGenerator(impl: WorkScheduleGeneratorImpl): ScheduleGenerator

    @Binds
    fun bindDayGenerator(impl: DaysGeneratorImpl): DaysGenerator

    @Binds
    fun bindFormatter(impl: DateFormatterImpl): DateFormatter

    @Binds
    fun bindDaysFabric(impl: WorkDaysFabricAdapter): DaysFabric

    @Binds
    fun bindWorkDaysFabric(impl: WorkDaysFabricImpl): WorkDaysFabric

    @Binds
    fun bindScheduleSetup(impl: TwoInTwoWorkScheduleSetup): ScheduleSetup

    @Binds

    fun bindDateContainer(impl: DateContainerImpl): DateContainer
}
