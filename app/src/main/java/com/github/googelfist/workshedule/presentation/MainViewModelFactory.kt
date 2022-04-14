package com.github.googelfist.workshedule.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.googelfist.workshedule.domain.usecases.GenerateCurrentMonthDefaultUseCase
import com.github.googelfist.workshedule.domain.usecases.GenerateNextMonthDefaultUseCase
import com.github.googelfist.workshedule.domain.usecases.GeneratePreviousMonthDefaultUseCase
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
    private val generatePreviousMonthDefaultUseCase: GeneratePreviousMonthDefaultUseCase,
    private val generateCurrentMonthDefaultUseCase: GenerateCurrentMonthDefaultUseCase,
    private val generateNextMonthDefaultUseCase: GenerateNextMonthDefaultUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(
            generatePreviousMonthDefaultUseCase = generatePreviousMonthDefaultUseCase,
            generateCurrentMonthDefaultUseCase = generateCurrentMonthDefaultUseCase,
            generateNextMonthDefaultUseCase = generateNextMonthDefaultUseCase
        ) as T
    }
}