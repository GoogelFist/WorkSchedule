package com.github.googelfist.workshedule.presentation.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.googelfist.workshedule.domain.usecases.EditDayTypeUseCase
import com.github.googelfist.workshedule.domain.usecases.GetCurrentMonthStateUseCase
import com.github.googelfist.workshedule.domain.usecases.GetNextMonthStateUseCase
import com.github.googelfist.workshedule.domain.usecases.GetPreviousMonthStateUseCase
import com.github.googelfist.workshedule.domain.usecases.LoadFirstWorkDateUseCase
import com.github.googelfist.workshedule.domain.usecases.LoadSchedulePatternUseCase
import com.github.googelfist.workshedule.domain.usecases.SaveFirstWorkDateUseCase
import com.github.googelfist.workshedule.domain.usecases.SaveSchedulePatternUseCase
import javax.inject.Inject

class ScheduleViewModelFactory @Inject constructor(
    private val getPreviousMonthStateUseCase: GetPreviousMonthStateUseCase,
    private val getCurrentMonthStateUseCase: GetCurrentMonthStateUseCase,
    private val getNextMonthStateUseCase: GetNextMonthStateUseCase,
    private val saveFirstWorkDateUseCase: SaveFirstWorkDateUseCase,
    private val loadFirstWorkDateUseCase: LoadFirstWorkDateUseCase,
    private val saveSchedulePatternUseCase: SaveSchedulePatternUseCase,
    private val loadSchedulePatternUseCase: LoadSchedulePatternUseCase,
    private val editDayTypeUseCase: EditDayTypeUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScheduleViewModel::class.java)) {
            return ScheduleViewModel(
                getPreviousMonthStateUseCase = getPreviousMonthStateUseCase,
                getCurrentMonthStateUseCase = getCurrentMonthStateUseCase,
                getNextMonthStateUseCase = getNextMonthStateUseCase,
                saveFirstWorkDateUseCase = saveFirstWorkDateUseCase,
                loadFirstWorkDateUseCase = loadFirstWorkDateUseCase,
                saveSchedulePatternUseCase = saveSchedulePatternUseCase,
                loadSchedulePatternUseCase = loadSchedulePatternUseCase,
                editDayTypeUseCase = editDayTypeUseCase
            ) as T
        }
        throw RuntimeException("Unknown class name")
    }
}