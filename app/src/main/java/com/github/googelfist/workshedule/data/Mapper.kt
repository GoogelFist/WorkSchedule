package com.github.googelfist.workshedule.data

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class Mapper @Inject constructor() {

    fun mapDateStringToLocalDate(string: String): LocalDate {
        return LocalDate.from(DateTimeFormatter.ofPattern(PATTERN).parse(string))
    }

    companion object {
        private const val PATTERN = "yyyy-M-d"
    }
}