package com.github.googelfist.workschedule.domain

import com.github.googelfist.workschedule.domain.models.PreferencesDTO

interface PreferenceRepository {
    fun loadPreference(): PreferencesDTO

    fun savePreference(preference: PreferencesDTO)
}
