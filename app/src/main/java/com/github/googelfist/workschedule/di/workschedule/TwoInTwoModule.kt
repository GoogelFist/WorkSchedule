package com.github.googelfist.workschedule.di.workschedule

import com.github.googelfist.workschedule.data.schedulegenerator.WorkScheduleGeneratorImpl
import com.github.googelfist.workschedule.data.schedulegenerator.daysgenerator.DaysGenerator
import com.github.googelfist.workschedule.data.schedulegenerator.daysgenerator.DaysGeneratorImpl
import com.github.googelfist.workschedule.data.schedulegenerator.fabric.DaysFabric
import com.github.googelfist.workschedule.data.schedulegenerator.fabric.WorkDaysFabric
import com.github.googelfist.workschedule.data.schedulegenerator.fabric.WorkDaysFabricAdapter
import com.github.googelfist.workschedule.data.schedulegenerator.fabric.WorkDaysFabricImpl
import com.github.googelfist.workschedule.data.schedulegenerator.formatter.DateFormatter
import com.github.googelfist.workschedule.data.schedulegenerator.formatter.DateFormatterImpl
import com.github.googelfist.workschedule.data.schedulegenerator.schedulesetup.ScheduleSetup
import com.github.googelfist.workschedule.data.schedulegenerator.schedulesetup.TwoInTwoWorkScheduleSetup
import com.github.googelfist.workschedule.domain.ScheduleGenerator
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
}
