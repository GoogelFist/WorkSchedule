package com.github.googelfist.workshedule.domain

import java.time.LocalDate

class GetDateNowUseCase {

    fun getDateNow(): LocalDate {
        return LocalDate.now()
    }
}