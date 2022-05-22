package com.github.googelfist.workshedule.presentation.screens.config

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.googelfist.workshedule.domain.usecases.*
import javax.inject.Inject

class ConfigViewModelFactory @Inject constructor(
    private val saveCurrentConfigIdUseCase: SaveCurrentConfigIdUseCase,
    private val saveFirstWorkDateUseCase: SaveFirstWorkDateUseCase,
    private val saveConfigNameUseCase: SaveConfigNameUseCase,
    private val loadScheduleConfigUseCase: LoadScheduleConfigUseCase,
    private val loadConfigListUseCase: LoadConfigListUseCase,
    private val createConfigUseCase: CreateConfigUseCase,
    private val deleteConfigUseCase: DeleteConfigUseCase,
    private val createDayTypeUseCase: CreateDayTypeUseCase,
    private val editDayTypeUseCase: EditDayTypeUseCase,
    private val deleteDayTypeUseCase: DeleteDayTypeUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ConfigViewModel::class.java)) {
            return ConfigViewModel(
                saveCurrentConfigIdUseCase = saveCurrentConfigIdUseCase,
                saveFirstWorkDateUseCase = saveFirstWorkDateUseCase,
                saveConfigNameUseCase = saveConfigNameUseCase,
                loadScheduleConfigUseCase = loadScheduleConfigUseCase,
                loadConfigListUseCase = loadConfigListUseCase,
                createConfigUseCase = createConfigUseCase,
                deleteConfigUseCase = deleteConfigUseCase,
                createDayTypeUseCase = createDayTypeUseCase,
                editDayTypeUseCase = editDayTypeUseCase,
                deleteDayTypeUseCase = deleteDayTypeUseCase
            ) as T
        }
        throw RuntimeException("Unknown class name")
    }
}