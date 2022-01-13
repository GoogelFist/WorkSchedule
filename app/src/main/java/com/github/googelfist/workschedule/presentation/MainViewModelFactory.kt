package com.github.googelfist.workschedule.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.googelfist.workschedule.data.schedulesgenerator.SchedulesGeneratorImpl
import com.github.googelfist.workschedule.data.schedulesgenerator.daysfabric.DaysFabric
import com.github.googelfist.workschedule.data.schedulesgenerator.daysfabric.DaysFabricImpl
import com.github.googelfist.workschedule.data.schedulesgenerator.daysgenerator.DaysGenerator
import com.github.googelfist.workschedule.data.schedulesgenerator.daysgenerator.DaysGeneratorImpl
import com.github.googelfist.workschedule.data.schedulesgenerator.daysmapper.DaysMapper
import com.github.googelfist.workschedule.data.schedulesgenerator.daysmapper.DaysMapperImpl
import com.github.googelfist.workschedule.domain.SchedulesGenerator
import com.github.googelfist.workschedule.domain.usecase.FormatDateUseCase
import com.github.googelfist.workschedule.domain.usecase.GenerateMonthUseCase
import com.github.googelfist.workschedule.domain.usecase.GenerateNextMonthUseCase
import com.github.googelfist.workschedule.domain.usecase.GenerateNextWorkMonthUseCase
import com.github.googelfist.workschedule.domain.usecase.GeneratePreviousMonthUseCase
import com.github.googelfist.workschedule.domain.usecase.GeneratePreviousWorkMonthUseCase
import com.github.googelfist.workschedule.domain.usecase.GenerateWorkMonthUseCase
import com.github.googelfist.workschedule.domain.usecase.GetActualDateFirstWorkUseCase
import com.github.googelfist.workschedule.domain.usecase.GetDateNowUseCase

class MainViewModelFactory : ViewModelProvider.Factory {
    private val daysFabric: DaysFabric = DaysFabricImpl()
    private val daysGenerator: DaysGenerator = DaysGeneratorImpl(daysFabric)
    private val dayMapper: DaysMapper = DaysMapperImpl()

    private val schedulesGenerator: SchedulesGenerator =
        SchedulesGeneratorImpl(daysGenerator, dayMapper)

    private val getDateOfChosenMonthUseCase = GetDateNowUseCase()
    private val formatDateUseCase = FormatDateUseCase()

    private val generateMonthUseCase = GenerateMonthUseCase(schedulesGenerator, formatDateUseCase)
    private val generateNextMonthUseCase = GenerateNextMonthUseCase(generateMonthUseCase)
    private val generatePreviousMonthUseCase = GeneratePreviousMonthUseCase(generateMonthUseCase)

    private val getActualDateFirstWorkUseCase = GetActualDateFirstWorkUseCase()

    private val generateWorkMonthUseCase =
        GenerateWorkMonthUseCase(schedulesGenerator, formatDateUseCase)

    private val generateNextWorkMonthUseCase =
        GenerateNextWorkMonthUseCase(generateWorkMonthUseCase, getActualDateFirstWorkUseCase)

    private val generatePreviousWorkMonthUseCase =
        GeneratePreviousWorkMonthUseCase(generateWorkMonthUseCase, getActualDateFirstWorkUseCase)

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(
            generateMonthUseCase,
            getDateOfChosenMonthUseCase,
            getActualDateFirstWorkUseCase,
            generateNextMonthUseCase,
            generatePreviousMonthUseCase,
            generateWorkMonthUseCase,
            generateNextWorkMonthUseCase,
            generatePreviousWorkMonthUseCase
        ) as T
    }
}
