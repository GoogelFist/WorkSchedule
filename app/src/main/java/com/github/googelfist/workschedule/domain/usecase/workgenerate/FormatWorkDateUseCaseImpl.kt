package com.github.googelfist.workschedule.domain.usecase.workgenerate

import androidx.lifecycle.LiveData
import com.github.googelfist.workschedule.domain.WorkScheduleGenerator
import com.github.googelfist.workschedule.domain.usecase.FormatDateUseCase

class FormatWorkDateUseCaseImpl(private val scheduleGenerator: WorkScheduleGenerator) :
    FormatDateUseCase {

    override operator fun invoke(): LiveData<String> {
        return scheduleGenerator.getActiveFormatDate()
    }
}
