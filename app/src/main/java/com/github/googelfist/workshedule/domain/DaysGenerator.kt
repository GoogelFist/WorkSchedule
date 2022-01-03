package com.github.googelfist.workshedule.domain

import androidx.lifecycle.LiveData
import com.github.googelfist.workshedule.domain.models.Day
import java.time.LocalDate

interface DaysGenerator {
    fun generateDays(date: LocalDate): LiveData<List<Day>>

    fun generateDays(date: LocalDate, startDay: LocalDate): List<Day>
}