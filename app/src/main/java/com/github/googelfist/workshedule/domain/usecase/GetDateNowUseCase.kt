package com.github.googelfist.workshedule.domain.usecase

import java.time.LocalDate

class GetDateNowUseCase {

    fun getDateNow(): LocalDate {
        return LocalDate.now()
    }
}