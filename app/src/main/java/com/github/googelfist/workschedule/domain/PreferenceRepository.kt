package com.github.googelfist.workschedule.domain

import com.github.googelfist.workschedule.domain.models.PreferencesModel

interface PreferenceRepository {
    fun loadPreference(): PreferencesModel
}
