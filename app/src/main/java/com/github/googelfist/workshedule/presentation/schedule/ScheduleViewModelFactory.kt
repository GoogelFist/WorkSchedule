package com.github.googelfist.workshedule.presentation.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.googelfist.workshedule.domain.usecases.twointwo.GenerateCurrentMonthTwoInTwoUseCase
import com.github.googelfist.workshedule.domain.usecases.twointwo.GenerateNextMonthTwoInTwoUseCase
import com.github.googelfist.workshedule.domain.usecases.twointwo.GeneratePreviousMonthTwoInTwoUseCase
import javax.inject.Inject

class ScheduleViewModelFactory @Inject constructor(
    private val generatePreviousMonthTwoInTwoUseCase: GeneratePreviousMonthTwoInTwoUseCase,
    private val generateCurrentMonthTwoInTwoUseCase: GenerateCurrentMonthTwoInTwoUseCase,
    private val generateNextMonthTwoInTwoUseCase: GenerateNextMonthTwoInTwoUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ScheduleViewModel(
            generatePreviousMonthTwoInTwoUseCase = generatePreviousMonthTwoInTwoUseCase,
            generateCurrentMonthTwoInTwoUseCase = generateCurrentMonthTwoInTwoUseCase,
            generateNextMonthTwoInTwoUseCase = generateNextMonthTwoInTwoUseCase
        ) as T
    }
}