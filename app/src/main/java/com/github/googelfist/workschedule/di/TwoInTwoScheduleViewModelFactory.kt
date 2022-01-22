package com.github.googelfist.workschedule.di

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.googelfist.workschedule.data.repository.PreferenceRepositoryImpl
import com.github.googelfist.workschedule.data.schedulegenerator.WorkScheduleGeneratorImpl
import com.github.googelfist.workschedule.data.schedulegenerator.daysgenerator.DaysGenerator
import com.github.googelfist.workschedule.data.schedulegenerator.daysgenerator.DaysGeneratorImpl
import com.github.googelfist.workschedule.data.schedulegenerator.fabric.WorkDaysFabric
import com.github.googelfist.workschedule.data.schedulegenerator.fabric.WorkDaysFabricAdapter
import com.github.googelfist.workschedule.data.schedulegenerator.fabric.WorkDaysFabricImpl
import com.github.googelfist.workschedule.data.schedulegenerator.formatter.DateFormatter
import com.github.googelfist.workschedule.data.schedulegenerator.formatter.DateFormatterImpl
import com.github.googelfist.workschedule.data.schedulegenerator.scheduletype.ScheduleSetup
import com.github.googelfist.workschedule.data.schedulegenerator.scheduletype.TwoInTwoWorkScheduleSetup
import com.github.googelfist.workschedule.domain.PreferenceRepository
import com.github.googelfist.workschedule.domain.ScheduleGenerator
import com.github.googelfist.workschedule.domain.usecase.FormatDateUseCase
import com.github.googelfist.workschedule.domain.usecase.generate.GenerateCurrentMonthUseCase
import com.github.googelfist.workschedule.domain.usecase.generate.GenerateNextMonthUseCase
import com.github.googelfist.workschedule.domain.usecase.generate.GeneratePreviousMonthUseCase
import com.github.googelfist.workschedule.presentation.ScheduleViewModel

class TwoInTwoScheduleViewModelFactory(application: Application) : ViewModelProvider.Factory {

    private val repository: PreferenceRepository = PreferenceRepositoryImpl(application)

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return getTwoInTwoScheduleViewModel() as T
    }

    private fun getTwoInTwoScheduleViewModel(): ScheduleViewModel {
        val daysFabric: WorkDaysFabric = WorkDaysFabricImpl()
        val scheduleSetup: ScheduleSetup = TwoInTwoWorkScheduleSetup()

        val fabricAdapter = WorkDaysFabricAdapter(
            workDaysFabric = daysFabric,
            preferenceRepository = repository,
            scheduleSetup = scheduleSetup
        )

        val formatter: DateFormatter = DateFormatterImpl()
        val daysGenerator: DaysGenerator = DaysGeneratorImpl(fabricAdapter)

        val scheduleGenerator: ScheduleGenerator = WorkScheduleGeneratorImpl(
            daysGenerator = daysGenerator,
            formatter = formatter
        )

        val generateCurrentMonthUseCase = GenerateCurrentMonthUseCase(scheduleGenerator)
        val generateNextMonthUseCase = GenerateNextMonthUseCase(scheduleGenerator)
        val generatePreviousMonthUseCase = GeneratePreviousMonthUseCase(scheduleGenerator)
        val formatDateUseCase = FormatDateUseCase(scheduleGenerator)

        return ScheduleViewModel(
            generateCurrentMonthUseCase = generateCurrentMonthUseCase,
            generateNextMonthUseCase = generateNextMonthUseCase,
            generatePreviousMonthUseCase = generatePreviousMonthUseCase,
            formatDateUseCase = formatDateUseCase
        )
    }
}
