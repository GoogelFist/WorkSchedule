package com.github.googelfist.workshedule.presentation.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.googelfist.workshedule.domain.models.month.Month
import com.github.googelfist.workshedule.domain.usecases.twointwo.GenerateCurrentMonthTwoInTwoUseCase
import com.github.googelfist.workshedule.domain.usecases.twointwo.GenerateNextMonthTwoInTwoUseCase
import com.github.googelfist.workshedule.domain.usecases.twointwo.GeneratePreviousMonthTwoInTwoUseCase
import java.time.LocalDate

class ScheduleViewModel(
    private val generatePreviousMonthTwoInTwoUseCase: GeneratePreviousMonthTwoInTwoUseCase,
    private val generateCurrentMonthTwoInTwoUseCase: GenerateCurrentMonthTwoInTwoUseCase,
    private val generateNextMonthTwoInTwoUseCase: GenerateNextMonthTwoInTwoUseCase
) : ViewModel() {

    private var _month = MutableLiveData<Month>()
    val month: LiveData<Month>
        get() = _month

//    init {
//        onGenerateCurrentMonth()
//    }

    fun onGenerateCurrentMonth(firstWorkDate: LocalDate) {
        _month.value = generateCurrentMonthTwoInTwoUseCase(firstWorkDate)
    }

    fun onGeneratePreviousMonth(firstWorkDate: LocalDate) {
        _month.value = generatePreviousMonthTwoInTwoUseCase(firstWorkDate)
    }

    fun onGenerateNextMonth(firstWorkDate: LocalDate) {
        _month.value = generateNextMonthTwoInTwoUseCase(firstWorkDate)
    }
}