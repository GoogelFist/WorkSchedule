package com.github.googelfist.workschedule.domain.usecase

import androidx.lifecycle.LiveData
import com.github.googelfist.workschedule.domain.ScheduleGenerator
import javax.inject.Inject

class FormatDateUseCase @Inject constructor(private val scheduleGenerator: ScheduleGenerator) {

    operator fun invoke(): LiveData<String> {
        return scheduleGenerator.getActiveFormatDate()
    }
}
