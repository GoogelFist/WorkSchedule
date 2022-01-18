package com.github.googelfist.workschedule.domain.usecase

import androidx.lifecycle.LiveData
import com.github.googelfist.workschedule.domain.models.days.Day

interface GeneratePreviousMonthUseCase {

    operator fun invoke(): LiveData<List<Day>>
}
