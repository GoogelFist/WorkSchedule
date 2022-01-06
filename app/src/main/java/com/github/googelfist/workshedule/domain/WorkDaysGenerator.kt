package com.github.googelfist.workshedule.domain

import androidx.lifecycle.LiveData
import com.github.googelfist.workshedule.domain.models.Day
import java.time.LocalDate

interface WorkDaysGenerator {
    fun generateWorkDays(date: LocalDate, firstWorkDate: LocalDate, step: Int): LiveData<List<Day>>
}