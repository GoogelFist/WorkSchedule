package com.github.googelfist.workschedule.domain.usecase

import androidx.lifecycle.LiveData

interface FormatDateUseCase {

    operator fun invoke(): LiveData<String>
}
