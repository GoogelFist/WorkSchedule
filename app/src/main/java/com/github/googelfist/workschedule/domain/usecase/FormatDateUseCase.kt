package com.github.googelfist.workschedule.domain.usecase

import androidx.lifecycle.LiveData
import com.github.googelfist.workschedule.domain.ScheduleGenerator

class FormatDateUseCase(private val scheduleGenerator: ScheduleGenerator) {

    operator fun invoke(): LiveData<String> {
        return scheduleGenerator.getActiveFormatDate()
    }
}
