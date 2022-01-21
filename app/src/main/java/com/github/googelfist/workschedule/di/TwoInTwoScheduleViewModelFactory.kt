package com.github.googelfist.workschedule.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.googelfist.workschedule.data.generator.formatter.DateFormatter
import com.github.googelfist.workschedule.data.generator.formatter.DateFormatterImpl
import com.github.googelfist.workschedule.data.generator.scheduletype.ScheduleTyper
import com.github.googelfist.workschedule.data.generator.scheduletype.TwoInTwoWorkScheduleTyperImpl
import com.github.googelfist.workschedule.data.generator.workschedule.WorkScheduleGeneratorImpl
import com.github.googelfist.workschedule.data.generator.workschedule.daysgenerator.WorkDaysGenerator
import com.github.googelfist.workschedule.data.generator.workschedule.daysgenerator.WorkDaysGeneratorImpl
import com.github.googelfist.workschedule.data.generator.workschedule.fabric.WorkDaysFabric
import com.github.googelfist.workschedule.data.generator.workschedule.fabric.WorkDaysFabricImpl
import com.github.googelfist.workschedule.data.repository.PreferenceRepositoryImpl
import com.github.googelfist.workschedule.domain.PreferenceRepository
import com.github.googelfist.workschedule.domain.WorkScheduleGenerator
import com.github.googelfist.workschedule.domain.usecase.FormatDateUseCase
import com.github.googelfist.workschedule.domain.usecase.GenerateMonthUseCase
import com.github.googelfist.workschedule.domain.usecase.preference.LoadPreferencesUseCase
import com.github.googelfist.workschedule.domain.usecase.workgenerate.FormatWorkDateUseCaseImpl
import com.github.googelfist.workschedule.domain.usecase.workgenerate.GenerateWorkCurrentMonthUseCaseImpl
import com.github.googelfist.workschedule.domain.usecase.workgenerate.GenerateWorkNextMonthUseCaseImpl
import com.github.googelfist.workschedule.domain.usecase.workgenerate.GenerateWorkPreviousMonthUseCaseImpl
import com.github.googelfist.workschedule.presentation.MainViewModel

class TwoInTwoScheduleViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val repository: PreferenceRepository = PreferenceRepositoryImpl(context)

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return getTwoInTwoScheduleViewModel() as T
    }

    private fun getTwoInTwoScheduleViewModel(): MainViewModel {
        val loadPreferencesUseCase = LoadPreferencesUseCase(repository)
        val daysFabric: WorkDaysFabric = WorkDaysFabricImpl()
        val twoInTwo: ScheduleTyper = TwoInTwoWorkScheduleTyperImpl()
        val formatter: DateFormatter = DateFormatterImpl()

        val daysGenerator: WorkDaysGenerator =
            WorkDaysGeneratorImpl(daysFabric = daysFabric, scheduleTyper = twoInTwo)

        val scheduleGenerator: WorkScheduleGenerator = WorkScheduleGeneratorImpl(
            daysGenerator = daysGenerator,
            formatter = formatter,
            scheduleTyper = twoInTwo
        )

        val generateCurrentMonthUseCase: GenerateMonthUseCase =
            GenerateWorkCurrentMonthUseCaseImpl(
                scheduleGenerator = scheduleGenerator,
                loadPreferencesUseCase = loadPreferencesUseCase
            )

        val generateNextMonthUseCase: GenerateMonthUseCase = GenerateWorkNextMonthUseCaseImpl(
            scheduleGenerator = scheduleGenerator,
            loadPreferencesUseCase = loadPreferencesUseCase
        )
        val generatePreviousMonthUseCase: GenerateMonthUseCase =
            GenerateWorkPreviousMonthUseCaseImpl(
                scheduleGenerator = scheduleGenerator,
                loadPreferencesUseCase = loadPreferencesUseCase
            )

        val formatDateUseCase: FormatDateUseCase = FormatWorkDateUseCaseImpl(scheduleGenerator)

        return MainViewModel(
            generateCurrentMonthUseCase = generateCurrentMonthUseCase,
            generateNextMonthUseCase = generateNextMonthUseCase,
            generatePreviousMonthUseCase = generatePreviousMonthUseCase,
            formatDateUseCase = formatDateUseCase
        )
    }
}
