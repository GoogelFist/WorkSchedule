package com.github.googelfist.workschedule.domain.usecase

import java.time.LocalDate

class GetDateNowUseCase {

    operator fun invoke(): LocalDate {
        return LocalDate.now()
    }
}
