package com.github.googelfist.workschedule.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.googelfist.workschedule.data.defaultschedulegenerator.DefaultScheduleGeneratorImpl
import com.github.googelfist.workschedule.data.defaultschedulegenerator.fabric.DefaultDaysFabric
import com.github.googelfist.workschedule.data.defaultschedulegenerator.fabric.DefaultDaysFabricImpl
import com.github.googelfist.workschedule.data.defaultschedulegenerator.generator.DefaultDaysGenerator
import com.github.googelfist.workschedule.data.defaultschedulegenerator.generator.DefaultDaysGeneratorImpl
import com.github.googelfist.workschedule.data.repository.PreferenceRepositoryImpl
import com.github.googelfist.workschedule.data.schedulesgenerator.TwoInTwoScheduleGeneratorImpl
import com.github.googelfist.workschedule.data.schedulesgenerator.fabric.TwoInTwoWorkDaysFabricImpl
import com.github.googelfist.workschedule.data.schedulesgenerator.fabric.WorkDaysFabric
import com.github.googelfist.workschedule.data.schedulesgenerator.generator.WorkDaysGenerator
import com.github.googelfist.workschedule.data.schedulesgenerator.generator.WorkDaysGeneratorImpl
import com.github.googelfist.workschedule.domain.DefaultScheduleGenerator
import com.github.googelfist.workschedule.domain.PreferenceRepository
import com.github.googelfist.workschedule.domain.ScheduleGenerator
import com.github.googelfist.workschedule.domain.usecase.FormatDateUseCase
import com.github.googelfist.workschedule.domain.usecase.GenerateDefaultMontUseCaseImpl
import com.github.googelfist.workschedule.domain.usecase.GenerateMonthUseCase
import com.github.googelfist.workschedule.domain.usecase.GenerateNextMonthUseCase
import com.github.googelfist.workschedule.domain.usecase.GeneratePreviousMonthUseCase
import com.github.googelfist.workschedule.domain.usecase.GenerateWorkMonthUseCaseImpl
import com.github.googelfist.workschedule.domain.usecase.GetActualDateFirstWorkUseCase
import com.github.googelfist.workschedule.domain.usecase.GetDateNowUseCase
import com.github.googelfist.workschedule.domain.usecase.LoadPreferencesUseCase
import com.github.googelfist.workschedule.domain.usecase.SavePreferenceUseCase

class MainViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val preferenceRepository: PreferenceRepository =
        PreferenceRepositoryImpl(context = context)

    private val loadPreferencesUseCase =
        LoadPreferencesUseCase(preferenceRepository = preferenceRepository)

    private val formatDateUseCase = FormatDateUseCase()

    private val getDateOfChosenMonthUseCase = GetDateNowUseCase()

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

        val daysGenerator: DefaultDaysGenerator = DefaultDaysGeneratorImpl(daysFabric = daysFabric)

        val scheduleGenerator: DefaultScheduleGenerator =
            DefaultScheduleGeneratorImpl(daysGenerator = daysGenerator)

        val generateMonthUseCase: GenerateMonthUseCase =
            GenerateDefaultMontUseCaseImpl(
                scheduleGenerator = scheduleGenerator,
                formatDateUseCase = formatDateUseCase
            )

        val generateNextMonthUseCase =
            GenerateNextMonthUseCase(generateMonthUseCase = generateMonthUseCase)

        val generatePreviousMonthUseCase =
            GeneratePreviousMonthUseCase(generateMonthUseCase = generateMonthUseCase)

        return MainViewModel(
            getDateNowUseCase = getDateOfChosenMonthUseCase,
            generateMonthUseCase = generateMonthUseCase,
            generateNextMonthUseCase = generateNextMonthUseCase,
            generatePreviousMonthUseCase = generatePreviousMonthUseCase
        )
    }

    private fun setupTwoInTwoSchedule(): MainViewModel {
        val daysFabric: WorkDaysFabric = TwoInTwoWorkDaysFabricImpl()

        val daysGenerator: WorkDaysGenerator = WorkDaysGeneratorImpl(workDaysFabric = daysFabric)

        val scheduleGenerator: ScheduleGenerator =
            TwoInTwoScheduleGeneratorImpl(workDaysGenerator = daysGenerator)

        val savePreferenceUseCase =
            SavePreferenceUseCase(preferenceRepository = preferenceRepository)

        val getActualDateFirstWorkUseCase = GetActualDateFirstWorkUseCase()

        val generateMonthUseCase: GenerateMonthUseCase =
            GenerateWorkMonthUseCaseImpl(
                scheduleGenerator = scheduleGenerator,
                formatDateUseCase = formatDateUseCase,
                loadPreferencesUseCase = loadPreferencesUseCase,
                savePreferenceUseCase = savePreferenceUseCase,
                getActualDateFirstWorkUseCase = getActualDateFirstWorkUseCase
            )

        val generateNextMonthUseCase =
            GenerateNextMonthUseCase(generateMonthUseCase = generateMonthUseCase)

        val generatePreviousMonthUseCase =
            GeneratePreviousMonthUseCase(generateMonthUseCase = generateMonthUseCase)

        return MainViewModel(
            getDateNowUseCase = getDateOfChosenMonthUseCase,
            generateMonthUseCase = generateMonthUseCase,
            generateNextMonthUseCase = generateNextMonthUseCase,
            generatePreviousMonthUseCase = generatePreviousMonthUseCase
        )
    }

    companion object {
        private const val TWO_IN_TWO_SCHEDULE = "2 / 2"
    }
}
