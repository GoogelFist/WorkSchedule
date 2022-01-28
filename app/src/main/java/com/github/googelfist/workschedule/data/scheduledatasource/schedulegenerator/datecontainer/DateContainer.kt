package com.github.googelfist.workschedule.data.scheduledatasource.schedulegenerator.datecontainer

import java.time.LocalDate

interface DateContainer {

    fun getDateNow(): LocalDate
}
