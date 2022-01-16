package com.github.googelfist.workschedule.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.googelfist.workschedule.data.repository.PreferenceRepositoryImpl
import com.github.googelfist.workschedule.data.schedulesgenerator.TwoInTwoScheduleGeneratorImpl
import com.github.googelfist.workschedule.data.schedulesgenerator.daysfabric.DaysFabric
import com.github.googelfist.workschedule.data.schedulesgenerator.daysfabric.TwoInTwoScheduleDaysFabricImpl
import com.github.googelfist.workschedule.data.schedulesgenerator.daysgenerator.DaysGenerator
import com.github.googelfist.workschedule.data.schedulesgenerator.daysgenerator.TwoInTwoDaysGeneratorImpl
import com.github.googelfist.workschedule.domain.PreferenceRepository
import com.github.googelfist.workschedule.domain.ScheduleGenerator
import com.github.googelfist.workschedule.domain.usecase.FormatDateUseCase
import com.github.googelfist.workschedule.domain.usecase.GenerateNextWorkMonthUseCase
import com.github.googelfist.workschedule.domain.usecase.GeneratePreviousWorkMonthUseCase
import com.github.googelfist.workschedule.domain.usecase.GenerateWorkMonthUseCase
import com.github.googelfist.workschedule.domain.usecase.GetActualDateFirstWorkUseCase
import com.github.googelfist.workschedule.domain.usecase.GetDateNowUseCase
import com.github.googelfist.workschedule.domain.usecase.LoadPreferencesUseCase
import com.github.googelfist.workschedule.domain.usecase.SavePreferenceUseCase

class MainViewModelFactory(context: Context) : ViewModelProvider.Factory {
    private val daysFabric: DaysFabric = TwoInTwoScheduleDaysFabricImpl()
    private val daysGenerator: DaysGenerator = TwoInTwoDaysGeneratorImpl(daysFabric = daysFabric)

    private val preferenceRepository: PreferenceRepository =
        PreferenceRepositoryImpl(context = context)

    private val scheduleGenerator: ScheduleGenerator =
        TwoInTwoScheduleGeneratorImpl(daysGenerator = daysGenerator)

    private val loadPreferencesUseCase =
        LoadPreferencesUseCase(preferenceRepository = preferenceRepository)

    private val savePreferenceUseCase =
        SavePreferenceUseCase(preferenceRepository = preferenceRepository)

    private val getDateOfChosenMonthUseCase = GetDateNowUseCase()
    private val formatDateUseCase = FormatDateUseCase()

    private val getActualDateFirstWorkUseCase =
        GetActualDateFirstWorkUseCase(scheduleGenerator = scheduleGenerator)

    private val generateWorkMonthUseCase =
        GenerateWorkMonthUseCase(
            scheduleGenerator = scheduleGenerator,
            formatDateUseCase = formatDateUseCase,
            loadPreferencesUseCase = loadPreferencesUseCase,
            savePreferenceUseCase = savePreferenceUseCase,
            getActualDateFirstWorkUseCase = getActualDateFirstWorkUseCase
        )

    private val generateNextWorkMonthUseCase =
        GenerateNextWorkMonthUseCase(generateWorkMonthUseCase = generateWorkMonthUseCase)

    private val generatePreviousWorkMonthUseCase =
        GeneratePreviousWorkMonthUseCase(generateWorkMonthUseCase = generateWorkMonthUseCase)

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(
            getDateNowUseCase = getDateOfChosenMonthUseCase,
            generateWorkMonthUseCase = generateWorkMonthUseCase,
            generateNextWorkMonthUseCase = generateNextWorkMonthUseCase,
            generatePreviousWorkMonthUseCase = generatePreviousWorkMonthUseCase
        ) as T
    }
}
