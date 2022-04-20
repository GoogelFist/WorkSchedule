package com.github.googelfist.workshedule.domain.usecases.twointwo

import com.github.googelfist.workshedule.domain.models.month.Month
import com.github.googelfist.workshedule.domain.monthgenerator.twointwo.WorkTwoInTwoMonthGenerator
import java.time.LocalDate
import javax.inject.Inject

class GenerateCurrentMonthTwoInTwoUseCase @Inject constructor(private val workDaysGenerator: WorkTwoInTwoMonthGenerator) {
    operator fun invoke(firstWorkDate: LocalDate): Month {
        return workDaysGenerator.generateCurrentMonth(firstWorkDate)
    }
}