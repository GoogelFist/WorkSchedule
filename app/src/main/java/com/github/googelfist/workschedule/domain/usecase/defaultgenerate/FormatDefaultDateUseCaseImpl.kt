package com.github.googelfist.workschedule.domain.usecase.defaultgenerate

import androidx.lifecycle.LiveData
import com.github.googelfist.workschedule.domain.DefaultScheduleGenerator
import com.github.googelfist.workschedule.domain.usecase.FormatDateUseCase

class FormatDefaultDateUseCaseImpl(private val scheduleGenerator: DefaultScheduleGenerator) :
    FormatDateUseCase {
    override operator fun invoke(): LiveData<String> {
        return scheduleGenerator.getActiveFormatDate()
    }
}
