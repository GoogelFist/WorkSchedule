package com.github.googelfist.workschedule.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.googelfist.workschedule.data.repository.PreferenceRepositoryImpl
import com.github.googelfist.workschedule.data.schedulesgenerator.default.DefaultScheduleGeneratorImpl
import com.github.googelfist.workschedule.data.schedulesgenerator.default.fabric.DefaultDaysFabric
import com.github.googelfist.workschedule.data.schedulesgenerator.default.fabric.DefaultDaysFabricImpl
import com.github.googelfist.workschedule.data.schedulesgenerator.default.generator.DefaultDaysGenerator
import com.github.googelfist.workschedule.data.schedulesgenerator.default.generator.DefaultDaysGeneratorImpl
import com.github.googelfist.workschedule.data.schedulesgenerator.work.TwoInTwoScheduleGeneratorImpl
import com.github.googelfist.workschedule.data.schedulesgenerator.work.fabric.TwoInTwoWorkDaysFabricImpl
import com.github.googelfist.workschedule.data.schedulesgenerator.work.fabric.WorkDaysFabric
import com.github.googelfist.workschedule.data.schedulesgenerator.work.generator.WorkDaysGenerator
import com.github.googelfist.workschedule.data.schedulesgenerator.work.generator.WorkDaysGeneratorImpl
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
import com.github.googelfist.workschedule.domain.usecase.preference.LoadPreferencesUseCase
import com.github.googelfist.workschedule.domain.usecase.preference.SavePreferenceUseCase

class MainViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val repository: PreferenceRepository = PreferenceRepositoryImpl(context)
    private val loadPreferencesUseCase = LoadPreferencesUseCase(repository)
    private val formatDateUseCase = FormatDateUseCase()
    private val getDateNowUseCase = GetDateNowUseCase()

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
        val generator: DefaultScheduleGenerator = DefaultScheduleGeneratorImpl(daysGenerator)

        val generateMonthUseCase: GenerateMonthUseCase =
            GenerateDefaultMontUseCaseImpl(
                scheduleGenerator = generator,
                formatDateUseCase = formatDateUseCase
            )

        val generateNextMonthUseCase = GenerateNextMonthUseCase(generateMonthUseCase)
        val generatePreviousMonthUseCase = GeneratePreviousMonthUseCase(generateMonthUseCase)

        return MainViewModel(
            getDateNowUseCase = getDateNowUseCase,
            generateMonthUseCase = generateMonthUseCase,
            generateNextMonthUseCase = generateNextMonthUseCase,
            generatePreviousMonthUseCase = generatePreviousMonthUseCase
        )
    }

    private fun setupTwoInTwoSchedule(): MainViewModel {
        val daysFabric: WorkDaysFabric = TwoInTwoWorkDaysFabricImpl()
        val daysGenerator: WorkDaysGenerator = WorkDaysGeneratorImpl(daysFabric)
        val scheduleGenerator: ScheduleGenerator = TwoInTwoScheduleGeneratorImpl(daysGenerator)
        val savePreferenceUseCase = SavePreferenceUseCase(repository)
        val getActualDateFirstWorkUseCase = GetActualDateFirstWorkUseCase()

        val generateMonthUseCase: GenerateMonthUseCase =
            GenerateWorkMonthUseCaseImpl(
                scheduleGenerator = scheduleGenerator,
                formatDateUseCase = formatDateUseCase,
                loadPreferencesUseCase = loadPreferencesUseCase,
                savePreferenceUseCase = savePreferenceUseCase,
                getActualDateFirstWorkUseCase = getActualDateFirstWorkUseCase
            )

        val generateNextMonthUseCase = GenerateNextMonthUseCase(generateMonthUseCase)
        val generatePreviousMonthUseCase = GeneratePreviousMonthUseCase(generateMonthUseCase)

        return MainViewModel(
            getDateNowUseCase = getDateNowUseCase,
            generateMonthUseCase = generateMonthUseCase,
            generateNextMonthUseCase = generateNextMonthUseCase,
            generatePreviousMonthUseCase = generatePreviousMonthUseCase
        )
    }

    companion object {
        private const val TWO_IN_TWO_SCHEDULE = "2 / 2"
    }
}
