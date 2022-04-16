package com.github.googelfist.workshedule.domain.monthgenerator

import java.time.LocalDate
import javax.inject.Inject

class DateNowContainer @Inject constructor() {
    fun getDate(): LocalDate {
        return LocalDate.now()
    }
}