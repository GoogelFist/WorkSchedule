package com.github.googelfist.workschedule.data.preferencedatasource

import com.github.googelfist.workschedule.data.preferencedatasource.model.PreferencesData

interface PreferenceDataSource {
    fun loadPreference(): PreferencesData

    fun savePreference(preference: PreferencesData)

    fun generateDefaultPreference(): PreferencesData
}
