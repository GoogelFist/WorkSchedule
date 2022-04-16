package com.github.googelfist.workshedule.presentation.def

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.googelfist.workshedule.domain.usecases.def.GenerateCurrentMonthDefaultUseCase
import com.github.googelfist.workshedule.domain.usecases.def.GenerateNextMonthDefaultUseCase
import com.github.googelfist.workshedule.domain.usecases.def.GeneratePreviousMonthDefaultUseCase
import javax.inject.Inject

class DefaultViewModelFactory @Inject constructor(
    private val generatePreviousMonthDefaultUseCase: GeneratePreviousMonthDefaultUseCase,
    private val generateCurrentMonthDefaultUseCase: GenerateCurrentMonthDefaultUseCase,
    private val generateNextMonthDefaultUseCase: GenerateNextMonthDefaultUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DefaultViewModel(
            generatePreviousMonthDefaultUseCase = generatePreviousMonthDefaultUseCase,
            generateCurrentMonthDefaultUseCase = generateCurrentMonthDefaultUseCase,
            generateNextMonthDefaultUseCase = generateNextMonthDefaultUseCase
        ) as T
    }
}