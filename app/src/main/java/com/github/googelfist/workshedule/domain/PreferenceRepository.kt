package com.github.googelfist.workshedule.domain

import com.github.googelfist.workshedule.domain.models.PreferencesModel

interface PreferenceRepository {
    fun loadPreference(): PreferencesModel
}
