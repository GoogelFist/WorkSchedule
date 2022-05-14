package com.github.googelfist.workshedule.presentation.config

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.googelfist.workshedule.domain.usecases.CreateDayTypeUseCase
import com.github.googelfist.workshedule.domain.usecases.DeleteDayTypeUseCase
import com.github.googelfist.workshedule.domain.usecases.EditDayTypeUseCase
import com.github.googelfist.workshedule.domain.usecases.LoadScheduleConfigUseCase
import com.github.googelfist.workshedule.domain.usecases.SaveFirstWorkDateUseCase
import com.github.googelfist.workshedule.domain.usecases.SavePatternNameUseCase
import javax.inject.Inject

class ConfigViewModelFactory @Inject constructor(
    private val saveFirstWorkDateUseCase: SaveFirstWorkDateUseCase,
    private val savePatternNameUseCase: SavePatternNameUseCase,
    private val loadScheduleConfigUseCase: LoadScheduleConfigUseCase,
    private val createDayTypeUseCase: CreateDayTypeUseCase,
    private val editDayTypeUseCase: EditDayTypeUseCase,
    private val deleteDayTypeUseCase: DeleteDayTypeUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ConfigViewModel::class.java)) {
            return ConfigViewModel(
                saveFirstWorkDateUseCase = saveFirstWorkDateUseCase,
                savePatternNameUseCase = savePatternNameUseCase,
                loadScheduleConfigUseCase = loadScheduleConfigUseCase,
                createDayTypeUseCase = createDayTypeUseCase,
                editDayTypeUseCase = editDayTypeUseCase,
                deleteDayTypeUseCase = deleteDayTypeUseCase
            ) as T
        }
        throw RuntimeException("Unknown class name")
    }
}