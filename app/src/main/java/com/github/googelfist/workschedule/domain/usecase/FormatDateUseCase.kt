package com.github.googelfist.workschedule.domain.usecase

import androidx.lifecycle.LiveData
import com.github.googelfist.workschedule.domain.ScheduleRepository
import javax.inject.Inject

class FormatDateUseCase @Inject constructor(private val repository: ScheduleRepository) {

    operator fun invoke(): LiveData<String> {
        return repository.getActiveFormatDate()
    }
}
