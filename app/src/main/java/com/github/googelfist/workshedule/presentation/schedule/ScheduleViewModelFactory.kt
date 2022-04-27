package com.github.googelfist.workshedule.presentation.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.googelfist.workshedule.domain.usecases.GenerateCurrentMonthUseCase
import com.github.googelfist.workshedule.domain.usecases.GenerateNextMonthUseCase
import com.github.googelfist.workshedule.domain.usecases.GeneratePreviousMonthUseCase
import com.github.googelfist.workshedule.domain.usecases.LoadScheduleTypeUseCase
import com.github.googelfist.workshedule.domain.usecases.SaveFirstWorkDateUseCase
import com.github.googelfist.workshedule.domain.usecases.SaveScheduleTypeUseCase
import javax.inject.Inject

class ScheduleViewModelFactory @Inject constructor(
    private val generatePreviousMonthUseCase: GeneratePreviousMonthUseCase,
    private val generateCurrentMonthUseCase: GenerateCurrentMonthUseCase,
    private val generateNextMonthUseCase: GenerateNextMonthUseCase,
    private val saveFirstWorkDateUseCase: SaveFirstWorkDateUseCase,
    private val saveScheduleTypeUseCase: SaveScheduleTypeUseCase,
    private val loadScheduleTypeUseCase: LoadScheduleTypeUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScheduleViewModel::class.java)) {
            return ScheduleViewModel(
                generatePreviousMonthUseCase = generatePreviousMonthUseCase,
                generateCurrentMonthUseCase = generateCurrentMonthUseCase,
                generateNextMonthUseCase = generateNextMonthUseCase,
                saveFirstWorkDateUseCase = saveFirstWorkDateUseCase,
                saveScheduleTypeUseCase = saveScheduleTypeUseCase,
                loadScheduleTypeUseCase = loadScheduleTypeUseCase
            ) as T
        }
        throw RuntimeException("Unknown class name")
    }
}