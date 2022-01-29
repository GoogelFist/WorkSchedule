package com.github.googelfist.workschedule.domain.usecase

import androidx.lifecycle.LiveData
import com.github.googelfist.workschedule.domain.ScheduleGenerator
import javax.inject.Inject

class FormatDateUseCase @Inject constructor(private val generator: ScheduleGenerator) {

    operator fun invoke(): LiveData<String> {
        return generator.getActiveFormatDate()
    }
}
