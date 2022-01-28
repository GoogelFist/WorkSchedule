package com.github.googelfist.workschedule.domain

import com.github.googelfist.workschedule.data.preferencedatasource.model.PreferencesData

interface PreferenceRepository {

    fun loadPreference(): PreferencesData
}
