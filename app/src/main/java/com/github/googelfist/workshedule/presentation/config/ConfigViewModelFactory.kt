package com.github.googelfist.workshedule.presentation.config

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.googelfist.workshedule.domain.usecases.CreateDayTypeUseCase
import com.github.googelfist.workshedule.domain.usecases.DeleteDayTypeUseCase
import com.github.googelfist.workshedule.domain.usecases.EditDayTypeUseCase
import com.github.googelfist.workshedule.domain.usecases.LoadFirstWorkDateUseCase
import com.github.googelfist.workshedule.domain.usecases.LoadSchedulePatternUseCase
import com.github.googelfist.workshedule.domain.usecases.SaveFirstWorkDateUseCase
import com.github.googelfist.workshedule.domain.usecases.SaveSchedulePatternUseCase
import javax.inject.Inject

class ConfigViewModelFactory @Inject constructor(
    private val saveFirstWorkDateUseCase: SaveFirstWorkDateUseCase,
    private val loadFirstWorkDateUseCase: LoadFirstWorkDateUseCase,
    private val saveSchedulePatternUseCase: SaveSchedulePatternUseCase,
    private val loadSchedulePatternUseCase: LoadSchedulePatternUseCase,
    private val createDayTypeUseCase: CreateDayTypeUseCase,
    private val editDayTypeUseCase: EditDayTypeUseCase,
    private val deleteDayTypeUseCase: DeleteDayTypeUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ConfigViewModel::class.java)) {
            return ConfigViewModel(
                saveFirstWorkDateUseCase = saveFirstWorkDateUseCase,
                loadFirstWorkDateUseCase = loadFirstWorkDateUseCase,
                saveSchedulePatternUseCase = saveSchedulePatternUseCase,
                loadSchedulePatternUseCase = loadSchedulePatternUseCase,
                createDayTypeUseCase = createDayTypeUseCase,
                editDayTypeUseCase = editDayTypeUseCase,
                deleteDayTypeUseCase = deleteDayTypeUseCase
            ) as T
        }
        throw RuntimeException("Unknown class name")
    }
}