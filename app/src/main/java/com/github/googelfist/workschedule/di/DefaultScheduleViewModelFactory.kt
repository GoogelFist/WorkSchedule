package com.github.googelfist.workschedule.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.googelfist.workschedule.data.schedulegenerator.DefaultScheduleGeneratorImp
import com.github.googelfist.workschedule.data.schedulegenerator.daysgenerator.DaysGenerator
import com.github.googelfist.workschedule.data.schedulegenerator.daysgenerator.DaysGeneratorImpl
import com.github.googelfist.workschedule.data.schedulegenerator.fabric.DaysFabric
import com.github.googelfist.workschedule.data.schedulegenerator.fabric.DefaultDaysFabricImpl
import com.github.googelfist.workschedule.data.schedulegenerator.formatter.DateFormatter
import com.github.googelfist.workschedule.data.schedulegenerator.formatter.DateFormatterImpl
import com.github.googelfist.workschedule.domain.ScheduleGenerator
import com.github.googelfist.workschedule.domain.usecase.FormatDateUseCase
import com.github.googelfist.workschedule.domain.usecase.generate.GenerateCurrentMonthUseCase
import com.github.googelfist.workschedule.domain.usecase.generate.GenerateNextMonthUseCase
import com.github.googelfist.workschedule.domain.usecase.generate.GeneratePreviousMonthUseCase
import com.github.googelfist.workschedule.presentation.ScheduleViewModel

class DefaultScheduleViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return getDefaultScheduleViewModel() as T
    }

    private fun getDefaultScheduleViewModel(): ScheduleViewModel {
        val daysFabric: DaysFabric = DefaultDaysFabricImpl()
        val daysGenerator: DaysGenerator = DaysGeneratorImpl(daysFabric)
        val formatter: DateFormatter = DateFormatterImpl()

        val scheduleGenerator: ScheduleGenerator =
            DefaultScheduleGeneratorImp(daysGenerator = daysGenerator, formatter = formatter)

        val generateMonthUseCase = GenerateCurrentMonthUseCase(scheduleGenerator)
        val generateNextMonthUseCase = GenerateNextMonthUseCase(scheduleGenerator)
        val generatePreviousMonthUseCase = GeneratePreviousMonthUseCase(scheduleGenerator)
        val formatDateUseCase = FormatDateUseCase(scheduleGenerator)

        return ScheduleViewModel(
            generateCurrentMonthUseCase = generateMonthUseCase,
            generateNextMonthUseCase = generateNextMonthUseCase,
            generatePreviousMonthUseCase = generatePreviousMonthUseCase,
            formatDateUseCase = formatDateUseCase
        )
    }
}
