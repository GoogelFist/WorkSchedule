package com.github.googelfist.workshedule.domain.models

import androidx.lifecycle.LiveData
import com.github.googelfist.workshedule.domain.models.days.Day
import java.time.LocalDate

data class MonthDTO(
    val formattedDateLD: LiveData<String>,
    val date: LocalDate,
    val dayListLD: LiveData<List<Day>>
)
