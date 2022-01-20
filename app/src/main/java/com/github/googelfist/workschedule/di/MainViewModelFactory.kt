package com.github.googelfist.workschedule.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.googelfist.workschedule.data.repository.PreferenceRepositoryImpl
import com.github.googelfist.workschedule.data.schedulesgenerator.DefaultScheduleGeneratorImp
import com.github.googelfist.workschedule.data.schedulesgenerator.WorkScheduleGeneratorImpl
import com.github.googelfist.workschedule.data.schedulesgenerator.daysgenerator.DefaultDaysGenerator
import com.github.googelfist.workschedule.data.schedulesgenerator.daysgenerator.DefaultDaysGeneratorImpl
import com.github.googelfist.workschedule.data.schedulesgenerator.daysgenerator.WorkDaysGenerator
import com.github.googelfist.workschedule.data.schedulesgenerator.daysgenerator.WorkDaysGeneratorImpl
import com.github.googelfist.workschedule.data.schedulesgenerator.fabric.DefaultDaysFabric
import com.github.googelfist.workschedule.data.schedulesgenerator.fabric.DefaultDaysFabricImpl
import com.github.googelfist.workschedule.data.schedulesgenerator.fabric.WorkDaysFabric
import com.github.googelfist.workschedule.data.schedulesgenerator.fabric.WorkDaysFabricImpl
import com.github.googelfist.workschedule.data.schedulesgenerator.formatter.DateFormatter
import com.github.googelfist.workschedule.data.schedulesgenerator.formatter.DateFormatterImpl
import com.github.googelfist.workschedule.data.schedulesgenerator.scheduletype.ScheduleType
import com.github.googelfist.workschedule.data.schedulesgenerator.scheduletype.TwoInTwoWorkScheduleImpl
import com.github.googelfist.workschedule.domain.DefaultScheduleGenerator
import com.github.googelfist.workschedule.domain.PreferenceRepository
import com.github.googelfist.workschedule.domain.WorkScheduleGenerator
import com.github.googelfist.workschedule.domain.usecase.FormatDateUseCase
import com.github.googelfist.workschedule.domain.usecase.GenerateMonthUseCase
import com.github.googelfist.workschedule.domain.usecase.defaultgenerate.FormatDefaultDateUseCaseImpl
import com.github.googelfist.workschedule.domain.usecase.defaultgenerate.GenerateDefaultCurrentMontUseCaseImpl
import com.github.googelfist.workschedule.domain.usecase.defaultgenerate.GenerateDefaultNextMonthUseCaseImpl
import com.github.googelfist.workschedule.domain.usecase.defaultgenerate.GenerateDefaultPreviousMonthUseCaseImpl
import com.github.googelfist.workschedule.domain.usecase.preference.LoadPreferencesUseCase
import com.github.googelfist.workschedule.domain.usecase.workgenerate.FormatWorkDateUseCaseImpl
import com.github.googelfist.workschedule.domain.usecase.workgenerate.GenerateWorkCurrentMonthUseCaseImpl
import com.github.googelfist.workschedule.domain.usecase.workgenerate.GenerateWorkNextMonthUseCaseImpl
import com.github.googelfist.workschedule.domain.usecase.workgenerate.GenerateWorkPreviousMonthUseCaseImpl
import com.github.googelfist.workschedule.presentation.MainViewModel

class MainViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val repository: PreferenceRepository = PreferenceRepositoryImpl(context)
    private val loadPreferencesUseCase = LoadPreferencesUseCase(repository)

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return getModel() as T
    }

    private fun getModel(): MainViewModel {
        return when (loadPreferencesUseCase().scheduleType) {
            TWO_IN_TWO_SCHEDULE -> setupTwoInTwoSchedule()
            else -> setupDefaultSchedule()
        }
    }

    private fun setupDefaultSchedule(): MainViewModel {
        val daysFabric: DefaultDaysFabric = DefaultDaysFabricImpl()
        val daysGenerator: DefaultDaysGenerator = DefaultDaysGeneratorImpl(daysFabric)
        val formatter: DateFormatter = DateFormatterImpl()

        val scheduleGenerator: DefaultScheduleGenerator =
            DefaultScheduleGeneratorImp(daysGenerator, formatter)

        val generateMonthUseCase: GenerateMonthUseCase =
            GenerateDefaultCurrentMontUseCaseImpl(scheduleGenerator = scheduleGenerator)

        val generateNextMonthUseCase: GenerateMonthUseCase =
            GenerateDefaultNextMonthUseCaseImpl(scheduleGenerator)

        val generatePreviousMonthUseCase: GenerateMonthUseCase =
            GenerateDefaultPreviousMonthUseCaseImpl(scheduleGenerator)

        val formatDateUseCase: FormatDateUseCase = FormatDefaultDateUseCaseImpl(scheduleGenerator)

        return MainViewModel(
            generateCurrentMonthUseCase = generateMonthUseCase,
            generateNextMonthUseCase = generateNextMonthUseCase,
            generatePreviousMonthUseCase = generatePreviousMonthUseCase,
            formatDateUseCase = formatDateUseCase
        )
    }

    private fun setupTwoInTwoSchedule(): MainViewModel {
        val daysFabric: WorkDaysFabric = WorkDaysFabricImpl()
        val twoInTwo: ScheduleType = TwoInTwoWorkScheduleImpl()
        val formatter: DateFormatter = DateFormatterImpl()

        val daysGenerator: WorkDaysGenerator =
            WorkDaysGeneratorImpl(daysFabric = daysFabric, scheduleType = twoInTwo)

        val scheduleGenerator: WorkScheduleGenerator = WorkScheduleGeneratorImpl(
            daysGenerator = daysGenerator,
            formatter = formatter,
            scheduleType = twoInTwo
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

    companion object {
        private const val TWO_IN_TWO_SCHEDULE = "2 / 2"
    }
}
