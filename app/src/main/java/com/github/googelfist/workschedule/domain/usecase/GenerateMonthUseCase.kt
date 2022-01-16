package com.github.googelfist.workschedule.domain.usecase

import com.github.googelfist.workschedule.domain.models.MonthDTO
import java.time.LocalDate

interface GenerateMonthUseCase {

    fun generate(date: LocalDate): MonthDTO
}
