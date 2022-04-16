package com.github.googelfist.workshedule.presentation.def

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.googelfist.workshedule.domain.models.month.Month
import com.github.googelfist.workshedule.domain.usecases.def.GenerateCurrentMonthDefaultUseCase
import com.github.googelfist.workshedule.domain.usecases.def.GenerateNextMonthDefaultUseCase
import com.github.googelfist.workshedule.domain.usecases.def.GeneratePreviousMonthDefaultUseCase

class DefaultViewModel(
    private val generatePreviousMonthDefaultUseCase: GeneratePreviousMonthDefaultUseCase,
    private val generateCurrentMonthDefaultUseCase: GenerateCurrentMonthDefaultUseCase,
    private val generateNextMonthDefaultUseCase: GenerateNextMonthDefaultUseCase
) : ViewModel() {

    private var _month = MutableLiveData<Month>()
    val month: LiveData<Month>
        get() = _month

    init {
        onGenerateCurrentMonth()
    }

    fun onGenerateCurrentMonth() {
        _month.value = generateCurrentMonthDefaultUseCase()
    }

    fun onGeneratePreviousMonth() {
        _month.value = generatePreviousMonthDefaultUseCase()
    }

    fun onGenerateNextMonth() {
        _month.value = generateNextMonthDefaultUseCase()
    }
}