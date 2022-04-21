package com.github.googelfist.workshedule.presentation.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.googelfist.workshedule.domain.models.month.Month
import com.github.googelfist.workshedule.domain.usecases.twointwo.GenerateCurrentMonthTwoInTwoUseCase
import com.github.googelfist.workshedule.domain.usecases.twointwo.GenerateNextMonthTwoInTwoUseCase
import com.github.googelfist.workshedule.domain.usecases.twointwo.GeneratePreviousMonthTwoInTwoUseCase
import com.github.googelfist.workshedule.domain.usecases.twointwo.LoadFirstWorkDateUseCase
import com.github.googelfist.workshedule.domain.usecases.twointwo.LoadScheduleTypeUseCase
import com.github.googelfist.workshedule.domain.usecases.twointwo.SaveFirstWorkDateUseCase
import com.github.googelfist.workshedule.domain.usecases.twointwo.SaveScheduleTypeUseCase
import java.time.LocalDate

class ScheduleViewModel(
    private val generatePreviousMonthTwoInTwoUseCase: GeneratePreviousMonthTwoInTwoUseCase,
    private val generateCurrentMonthTwoInTwoUseCase: GenerateCurrentMonthTwoInTwoUseCase,
    private val generateNextMonthTwoInTwoUseCase: GenerateNextMonthTwoInTwoUseCase,
    private val loadFirstWorkDateUseCase: LoadFirstWorkDateUseCase,
    private val saveFirstWorkDateUseCase: SaveFirstWorkDateUseCase,
    private val loadScheduleTypeUseCase: LoadScheduleTypeUseCase,
    private val saveScheduleTypeUseCase: SaveScheduleTypeUseCase,
) : ViewModel() {

    private var _month = MutableLiveData<Month>()
    val month: LiveData<Month>
        get() = _month

//    init {
//        onGenerateCurrentMonth()
//    }

    val firstWorkDate = loadFirstWorkDateUseCase()

    fun onGenerateCurrentMonth(firstWorkDate: LocalDate) {
        _month.value = generateCurrentMonthTwoInTwoUseCase(firstWorkDate)
    }

    fun onGeneratePreviousMonth(firstWorkDate: LocalDate) {
        _month.value = generatePreviousMonthTwoInTwoUseCase(firstWorkDate)
    }

    fun onGenerateNextMonth(firstWorkDate: LocalDate) {
        _month.value = generateNextMonthTwoInTwoUseCase(firstWorkDate)
    }

    fun onSaveFirstWorkDate(firstWorkDate: String) {
        saveFirstWorkDateUseCase(firstWorkDate)
    }
}