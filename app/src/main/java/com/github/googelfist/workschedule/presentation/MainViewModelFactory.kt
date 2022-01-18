package com.github.googelfist.workschedule.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.googelfist.workschedule.data.repository.PreferenceRepositoryImpl
import com.github.googelfist.workschedule.data.schedulesgenerator.ScheduleGeneratorImpl
import com.github.googelfist.workschedule.data.schedulesgenerator.daysgenerator.DaysGenerator
import com.github.googelfist.workschedule.data.schedulesgenerator.daysgenerator.DaysGeneratorImpl
import com.github.googelfist.workschedule.data.schedulesgenerator.fabric.DaysFabric
import com.github.googelfist.workschedule.data.schedulesgenerator.fabric.DaysFabricImpl
import com.github.googelfist.workschedule.data.schedulesgenerator.scheduletype.DefaultScheduleImpl
import com.github.googelfist.workschedule.data.schedulesgenerator.scheduletype.ScheduleType
import com.github.googelfist.workschedule.data.schedulesgenerator.scheduletype.TwoInTwoWorkScheduleImpl
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
    private val daysFabric: DaysFabric = DaysFabricImpl()

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
        val default: ScheduleType = DefaultScheduleImpl()

        val daysGenerator: DaysGenerator =
            DaysGeneratorImpl(daysFabric = daysFabric, scheduleType = default)

        val generator: ScheduleGenerator = ScheduleGeneratorImpl(daysGenerator)

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
        val twoInTwo: ScheduleType = TwoInTwoWorkScheduleImpl()

        val daysGenerator: DaysGenerator =
            DaysGeneratorImpl(daysFabric = daysFabric, scheduleType = twoInTwo)

        val scheduleGenerator: ScheduleGenerator = ScheduleGeneratorImpl(daysGenerator)
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
