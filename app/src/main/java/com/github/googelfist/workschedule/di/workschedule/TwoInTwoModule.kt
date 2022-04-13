package com.github.googelfist.workschedule.di.workschedule

import com.github.googelfist.workschedule.domain.ScheduleGenerator
import com.github.googelfist.workschedule.domain.schedulegenerator.WorkScheduleGeneratorImpl
import com.github.googelfist.workschedule.domain.schedulegenerator.datecontainer.DateContainer
import com.github.googelfist.workschedule.domain.schedulegenerator.datecontainer.DateContainerImpl
import com.github.googelfist.workschedule.domain.schedulegenerator.daysgenerator.DaysGenerator
import com.github.googelfist.workschedule.domain.schedulegenerator.daysgenerator.DaysGeneratorImpl
import com.github.googelfist.workschedule.domain.schedulegenerator.daysgenerator.fabric.DaysFabric
import com.github.googelfist.workschedule.domain.schedulegenerator.daysgenerator.fabric.WorkDaysFabric
import com.github.googelfist.workschedule.domain.schedulegenerator.daysgenerator.fabric.WorkDaysFabricAdapter
import com.github.googelfist.workschedule.domain.schedulegenerator.daysgenerator.fabric.WorkDaysFabricImpl
import com.github.googelfist.workschedule.domain.schedulegenerator.daysgenerator.schedulesetup.ScheduleSetup
import com.github.googelfist.workschedule.domain.schedulegenerator.daysgenerator.schedulesetup.TwoInTwoWorkScheduleSetup
import com.github.googelfist.workschedule.domain.schedulegenerator.formatter.DateFormatter
import com.github.googelfist.workschedule.domain.schedulegenerator.formatter.DateFormatterImpl
import dagger.Binds
import dagger.Module

@Module()
interface TwoInTwoModule {

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
