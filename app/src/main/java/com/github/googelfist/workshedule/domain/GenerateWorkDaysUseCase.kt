package com.github.googelfist.workshedule.domain

import androidx.lifecycle.LiveData
import java.time.LocalDate

class GenerateWorkDaysUseCase(private val daysGenerator: DaysGenerator) {

    fun generateWorkDays(date: LocalDate): LiveData<List<Day>> {
        return daysGenerator.generateDays(date)
    }
}