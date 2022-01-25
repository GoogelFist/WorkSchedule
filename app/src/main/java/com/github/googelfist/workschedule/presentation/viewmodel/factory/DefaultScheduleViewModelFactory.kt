package com.github.googelfist.workschedule.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.googelfist.workschedule.domain.usecase.FormatDateUseCase
import com.github.googelfist.workschedule.domain.usecase.generate.GenerateCurrentMonthUseCase
import com.github.googelfist.workschedule.domain.usecase.generate.GenerateNextMonthUseCase
import com.github.googelfist.workschedule.domain.usecase.generate.GeneratePreviousMonthUseCase
import com.github.googelfist.workschedule.presentation.viewmodel.ScheduleViewModel
import javax.inject.Inject

class DefaultScheduleViewModelFactory @Inject constructor(
    private val generateCurrentMonthUseCase: GenerateCurrentMonthUseCase,
    private val generateNextMonthUseCase: GenerateNextMonthUseCase,
    private val generatePreviousMonthUseCase: GeneratePreviousMonthUseCase,
    private val formatDateUseCase: FormatDateUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ScheduleViewModel(
            generateCurrentMonthUseCase = generateCurrentMonthUseCase,
            generateNextMonthUseCase = generateNextMonthUseCase,
            generatePreviousMonthUseCase = generatePreviousMonthUseCase,
            formatDateUseCase = formatDateUseCase
        ) as T
    }
}
