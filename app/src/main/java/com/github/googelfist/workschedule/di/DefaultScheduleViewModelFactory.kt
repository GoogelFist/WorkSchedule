package com.github.googelfist.workschedule.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.googelfist.workschedule.data.generator.defaultschedule.DefaultScheduleGeneratorImp
import com.github.googelfist.workschedule.data.generator.defaultschedule.daysgenerator.DefaultDaysGenerator
import com.github.googelfist.workschedule.data.generator.defaultschedule.daysgenerator.DefaultDaysGeneratorImpl
import com.github.googelfist.workschedule.data.generator.defaultschedule.fabric.DefaultDaysFabric
import com.github.googelfist.workschedule.data.generator.defaultschedule.fabric.DefaultDaysFabricImpl
import com.github.googelfist.workschedule.data.generator.formatter.DateFormatter
import com.github.googelfist.workschedule.data.generator.formatter.DateFormatterImpl
import com.github.googelfist.workschedule.domain.DefaultScheduleGenerator
import com.github.googelfist.workschedule.domain.usecase.FormatDateUseCase
import com.github.googelfist.workschedule.domain.usecase.GenerateMonthUseCase
import com.github.googelfist.workschedule.domain.usecase.defaultgenerate.FormatDefaultDateUseCaseImpl
import com.github.googelfist.workschedule.domain.usecase.defaultgenerate.GenerateDefaultCurrentMontUseCaseImpl
import com.github.googelfist.workschedule.domain.usecase.defaultgenerate.GenerateDefaultNextMonthUseCaseImpl
import com.github.googelfist.workschedule.domain.usecase.defaultgenerate.GenerateDefaultPreviousMonthUseCaseImpl
import com.github.googelfist.workschedule.presentation.MainViewModel

class DefaultScheduleViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return getDefaultScheduleViewModel() as T
    }

    private fun getDefaultScheduleViewModel(): MainViewModel {
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
}
