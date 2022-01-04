package com.github.googelfist.workshedule.domain.usecase

import androidx.lifecycle.LiveData
import com.github.googelfist.workshedule.domain.DaysGenerator
import com.github.googelfist.workshedule.domain.models.Day
import java.time.LocalDate

class GenerateWorkDaysUseCase(private val daysGenerator: DaysGenerator) {

    fun generateWorkDays(date: LocalDate): LiveData<List<Day>> {
        return daysGenerator.generateDays(date)
    }
}