package com.github.googelfist.workshedule.domain

import com.github.googelfist.workshedule.domain.models.DatePreference
import com.github.googelfist.workshedule.domain.models.SchedulePreference

interface DateRepository {
    fun saveDatePreference(datePreferenceModel: DatePreference)

    fun saveSchedulePreference(schedulePreferenceModel: SchedulePreference)

    fun loadPreference(): String
}