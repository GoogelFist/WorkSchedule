package com.github.googelfist.workshedule.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class FormatDateUseCase {
    private val formattedDateLD = MutableLiveData<String>()

    fun formatDate(date: LocalDate): LiveData<String> {
        val format = DateTimeFormatter.ofPattern(FORMAT_PATTERN).format(date)
        formattedDateLD.value = format
        return formattedDateLD
    }

    companion object {
        private const val FORMAT_PATTERN = "MMM yyyy"
    }
}