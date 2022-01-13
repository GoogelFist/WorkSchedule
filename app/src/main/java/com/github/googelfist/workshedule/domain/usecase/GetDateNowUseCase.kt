package com.github.googelfist.workshedule.domain.usecase

import java.time.LocalDate

class GetDateNowUseCase {

    operator fun invoke(): LocalDate {
        return LocalDate.now()
    }
}
