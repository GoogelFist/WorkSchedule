package com.github.googelfist.workschedule.domain.schedulegenerator.datecontainer

import java.time.LocalDate

interface DateContainer {

    fun getDateNow(): LocalDate
}
