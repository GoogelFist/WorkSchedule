package com.github.googelfist.workschedule.domain

import com.github.googelfist.workschedule.data.repository.model.Preferences

interface PreferenceRepository {
    fun loadPreference(): Preferences

    fun savePreference(preference: Preferences)
}
