package com.github.googelfist.workshedule.domain.models

import androidx.lifecycle.LiveData
import java.time.LocalDate

data class Month(
    val formattedDateLD: LiveData<String>,
    val date: LocalDate,
    val dayListLD: LiveData<List<Day>>
)
