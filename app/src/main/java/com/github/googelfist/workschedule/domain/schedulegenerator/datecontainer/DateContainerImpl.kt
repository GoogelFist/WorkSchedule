package com.github.googelfist.workschedule.domain.schedulegenerator.datecontainer

import java.time.LocalDate
import javax.inject.Inject

class DateContainerImpl @Inject constructor() : DateContainer {

    override fun getDateNow(): LocalDate {
        return LocalDate.now()
    }
}
