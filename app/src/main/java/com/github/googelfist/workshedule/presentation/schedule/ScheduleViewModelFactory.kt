package com.github.googelfist.workshedule.presentation.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.googelfist.workshedule.domain.usecases.twointwo.GenerateCurrentMonthTwoInTwoUseCase
import com.github.googelfist.workshedule.domain.usecases.twointwo.GenerateNextMonthTwoInTwoUseCase
import com.github.googelfist.workshedule.domain.usecases.twointwo.GeneratePreviousMonthTwoInTwoUseCase
import com.github.googelfist.workshedule.domain.usecases.twointwo.LoadFirstWorkDateUseCase
import com.github.googelfist.workshedule.domain.usecases.twointwo.LoadScheduleTypeUseCase
import com.github.googelfist.workshedule.domain.usecases.twointwo.SaveFirstWorkDateUseCase
import com.github.googelfist.workshedule.domain.usecases.twointwo.SaveScheduleTypeUseCase
import javax.inject.Inject

class ScheduleViewModelFactory @Inject constructor(
    private val generatePreviousMonthTwoInTwoUseCase: GeneratePreviousMonthTwoInTwoUseCase,
    private val generateCurrentMonthTwoInTwoUseCase: GenerateCurrentMonthTwoInTwoUseCase,
    private val generateNextMonthTwoInTwoUseCase: GenerateNextMonthTwoInTwoUseCase,
    private val loadFirstWorkDateUseCase: LoadFirstWorkDateUseCase,
    private val saveFirstWorkDateUseCase: SaveFirstWorkDateUseCase,
    private val loadScheduleTypeUseCase: LoadScheduleTypeUseCase,
    private val saveScheduleTypeUseCase: SaveScheduleTypeUseCase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ScheduleViewModel(
            generatePreviousMonthTwoInTwoUseCase = generatePreviousMonthTwoInTwoUseCase,
            generateCurrentMonthTwoInTwoUseCase = generateCurrentMonthTwoInTwoUseCase,
            generateNextMonthTwoInTwoUseCase = generateNextMonthTwoInTwoUseCase,
            loadFirstWorkDateUseCase = loadFirstWorkDateUseCase,
            saveFirstWorkDateUseCase = saveFirstWorkDateUseCase,
            loadScheduleTypeUseCase = loadScheduleTypeUseCase,
            saveScheduleTypeUseCase = saveScheduleTypeUseCase,
        ) as T
    }
}